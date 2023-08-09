package com.decemo.reservation.controller

import com.decemo.reservation.model.request.ReservationRequestBody
import com.decemo.reservation.model.response.ReservationResponseBody
import com.decemo.reservation.service.ReservationService
import decemo.com.userauthorisation.model.JwtTokenBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class ReservationController(private val service: ReservationService) {

    @PostMapping("/reserve")
    fun reserve(@RequestAttribute tokenBody: JwtTokenBody, @RequestBody reserve: ReservationRequestBody): ResponseEntity<ReservationResponseBody> {
        service.reserve(tokenBody.id, reserve).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping("/{id}")
    fun updateReservation(
        @RequestAttribute tokenBody: JwtTokenBody,
        @RequestBody reserve: ReservationRequestBody,
        @PathVariable id: Long
    ): ResponseEntity<ReservationResponseBody> {
        service.updateReservation(tokenBody.id, id, reserve).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @DeleteMapping("/reserve/{id}")
    fun updateReservation(@RequestAttribute tokenBody: JwtTokenBody, @PathVariable id: Long): ResponseEntity<Unit> {
        service.deleteReservation(tokenBody.id, id).onSuccess {
            return ResponseEntity.ok(Unit)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("/reservations")
    fun userReservations(@RequestAttribute tokenBody: JwtTokenBody): ResponseEntity<List<ReservationResponseBody>> {
        service.getUserReservations(tokenBody.id).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("/{id}")
    fun getReservation(@RequestAttribute tokenBody: JwtTokenBody, @PathVariable id:Long): ResponseEntity<ReservationResponseBody> {
        service.getUserReservation(tokenBody.id, id).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}