package com.decemo.reservation.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.ZonedDateTime

data class ReservationResponseBody(
    var id: Long,
    var numOfPersons: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    var reservationDay: LocalDate,
    val createdAt: ZonedDateTime,
    var barId: Long,
    var barName: String,
    var barImage: String
)