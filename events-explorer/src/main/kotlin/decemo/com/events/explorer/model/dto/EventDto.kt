package decemo.com.events.explorer.model.dto

import java.time.ZonedDateTime

data class EventDto(
    var id: Long = 0,
    var name: String,
    var imageUrl: String,
    var bar: BarDto,
    var createdAt: ZonedDateTime
)