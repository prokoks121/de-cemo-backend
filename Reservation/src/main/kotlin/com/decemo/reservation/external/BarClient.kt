package com.decemo.reservation.external

import com.decemo.reservation.external.model.BarResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "\${decemo.reservation.external.clientName}", url = "\${decemo.reservation.external.url}")
interface BarClient {
    companion object {
        private const val GET_BAR = "/api/v1/explorer/bar/{id}"
    }

    @RequestMapping(method = [RequestMethod.GET], value = [GET_BAR])
    fun getBar(@PathVariable("id") id: Long): BarResponse
}