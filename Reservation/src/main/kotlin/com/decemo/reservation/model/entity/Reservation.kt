package com.decemo.reservation.model.entity

import java.time.LocalDate
import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,
    var numOfPersons: Int,
    var reservationDay: LocalDate,
    val userId: Long,
    val barId: Long,
    val createdAt: ZonedDateTime = ZonedDateTime.now()
)