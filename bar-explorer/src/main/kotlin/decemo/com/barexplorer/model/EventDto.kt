package decemo.com.barexplorer.model

import java.time.ZonedDateTime

data class EventDto(
    var id: Long = 0,
    var name: String,
    var imageUrl: String,
    var createdAt:ZonedDateTime,
)