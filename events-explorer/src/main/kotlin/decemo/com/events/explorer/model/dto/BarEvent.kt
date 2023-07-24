package decemo.com.events.explorer.model.dto

data class BarEvent(
    var barId: Long,
    var barName: String,
    var barImageUrl: String,
    var events: List<EventDto>
)
