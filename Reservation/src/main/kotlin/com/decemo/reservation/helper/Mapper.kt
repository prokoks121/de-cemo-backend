package com.decemo.reservation.helper

import com.decemo.reservation.external.model.BarResponse
import com.decemo.reservation.model.entity.Reservation
import com.decemo.reservation.model.response.ReservationResponseBody

fun Reservation.mapToResponse(bar: BarResponse): ReservationResponseBody {
    return ReservationResponseBody(
        id,
        numOfPersons,
        reservationDay,
        createdAt,
        bar.id,
        bar.name,
        bar.mainPictureUrl
    )
}