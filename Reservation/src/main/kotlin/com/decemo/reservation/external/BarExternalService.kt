package com.decemo.reservation.external

import com.decemo.reservation.external.model.BarResponse
import org.springframework.stereotype.Service

@Service
class BarExternalService(private val barClient: BarClient) {

    fun getBar(barId: Long): Result<BarResponse> {
        return runCatching {
            barClient.getBar(barId)
        }
    }
}