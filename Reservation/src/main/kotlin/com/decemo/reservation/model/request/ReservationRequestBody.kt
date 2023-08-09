package com.decemo.reservation.model.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class ReservationRequestBody(
    val numOfPersons: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    val reservationDay: LocalDate,
    val barId: Long
)
