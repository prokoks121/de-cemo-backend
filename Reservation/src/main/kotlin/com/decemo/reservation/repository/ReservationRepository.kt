package com.decemo.reservation.repository

import com.decemo.reservation.model.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface ReservationRepository : JpaRepository<Reservation, Long> {
    fun findAllByUserIdAndReservationDayAfter(userId: Long, day: LocalDate): List<Reservation>

    fun findReservationByIdAndUserId(reservationId: Long, userId: Long): Optional<Reservation>

    fun deleteReservationByIdAndUserId(reservationId: Long, userId: Long)

    fun findByIdAndUserId(reservationId: Long, userId: Long): Optional<Reservation>
}