package decemo.com.events.explorer.model.dto

import decemo.com.bardatastore.entity.Bar

class EventDto(
    var id: Long = 0,
    var name: String,
    var imageUrl: String,
    var bar: Bar
)