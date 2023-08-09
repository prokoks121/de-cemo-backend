package com.decemo.reservation.service

import com.decemo.reservation.exception.BarNotFoundException
import com.decemo.reservation.exception.ReservationException
import com.decemo.reservation.exception.ReservationNotFoundException
import com.decemo.reservation.external.BarExternalService
import com.decemo.reservation.external.model.BarResponse
import com.decemo.reservation.helper.mapToResponse
import com.decemo.reservation.model.entity.Reservation
import com.decemo.reservation.model.request.ReservationRequestBody
import com.decemo.reservation.model.response.ReservationResponseBody
import com.decemo.reservation.repository.ReservationRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId
import javax.transaction.Transactional


@Service
class ReservationService(private val repository: ReservationRepository, private val externalSource: BarExternalService) {

    fun reserve(userId: Long, reserveRequestBody: ReservationRequestBody): Result<ReservationResponseBody> {
        runCatching {
            externalSource.getBar(reserveRequestBody.barId).onSuccess {
                val reservation = Reservation(
                    numOfPersons = reserveRequestBody.numOfPersons,
                    reservationDay = reserveRequestBody.reservationDay,
                    userId = userId,
                    barId = it.id,
                )
                val savedReservation = repository.save(reservation)
                val response = savedReservation.mapToResponse(it)
                return Result.success(response)
            }.onFailure {
                return Result.failure(BarNotFoundException("Bar with id ${reserveRequestBody.barId} not found."))
            }
        }.onFailure {
            return Result.failure(ReservationException(it.message))
        }
        return Result.failure(ReservationException("Somethings is wrong."))
    }

    fun getUserReservations(userId: Long): Result<List<ReservationResponseBody>> {
        runCatching {
            val reservations = repository.findAllByUserIdAndReservationDayAfter(userId, getTomorrowDate())
            val reservationResponse = reservations.map {
                mapReservationResponse(it)
            }
            return Result.success(reservationResponse.filterNotNull())
        }.onFailure {
            return Result.failure(ReservationException(it.message))
        }
        return Result.failure(ReservationException("Somethings is wrong."))
    }

    fun updateReservation(userId: Long, reservationId: Long, reservationRequestBody: ReservationRequestBody): Result<ReservationResponseBody> {
        runCatching {
            val reservation = repository.findReservationByIdAndUserId(reservationId, userId)
            if (reservation.isEmpty) {
                return Result.failure(ReservationNotFoundException("The reservation with id $reservationId and userId $userId not exist."))
            }
            var bar: BarResponse? = null
            externalSource.getBar(reservation.get().barId).onSuccess {
                bar = it
            }.onFailure {
                return Result.failure(BarNotFoundException("Bar with id ${reservationRequestBody.barId} not found."))
            }

            reservation.get().numOfPersons = reservationRequestBody.numOfPersons
            reservation.get().reservationDay = reservationRequestBody.reservationDay
            repository.save(reservation.get())
            return Result.success(reservation.get().mapToResponse(bar!!))
        }.onFailure {
            return Result.failure(ReservationException(it.message))
        }
        return Result.failure(ReservationException("Somethings is wrong."))
    }

    @Transactional
    fun deleteReservation(userId: Long, reservationId: Long): Result<Unit> {
        return runCatching {
            repository.deleteReservationByIdAndUserId(reservationId, userId)
        }.onFailure {
            return Result.failure(ReservationException(it.message))
        }
    }

    fun getUserReservation(userId: Long, reservationId: Long): Result<ReservationResponseBody> {
        runCatching {
            val reservation = repository.findByIdAndUserId(reservationId, userId)
            if (reservation.isEmpty) {
                return Result.failure(ReservationNotFoundException("Reservation not exist."))
            }
            val reservationResponse =
                mapReservationResponse(reservation.get()) ?: return Result.failure(BarNotFoundException("Bar for this reservation not found"))
            return Result.success(reservationResponse)
        }.onFailure {
            return Result.failure(ReservationException(it.message))
        }
        return Result.failure(ReservationException("Somethings is wrong."))
    }

    private fun mapReservationResponse(reservation: Reservation): ReservationResponseBody? {
        externalSource.getBar(reservation.barId).onSuccess {
            return reservation.mapToResponse(it)
        }.onFailure {
            return null
        }
        return null
    }

    private fun getTomorrowDate(): LocalDate {
        return LocalDate.from(LocalDate.now().minusDays(1))
    }
}