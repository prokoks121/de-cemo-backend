package decemo.com.events.explorer.model.dto

import decemo.com.bardatastore.entity.Event

data class BarDto(
    var id: Long,
    var name: String,
    var address: String,
    var services: MutableList<ServiceDto> = mutableListOf(),
    var workTime: String,
    var latitude: String,
    var longitude: String,
    var phoneNumber: String,
    var mainPictureUrl: String,
    var galleryPictureUrls: String,
    var barType: BarTypeDto,
    var events: MutableList<Event> = mutableListOf()
)