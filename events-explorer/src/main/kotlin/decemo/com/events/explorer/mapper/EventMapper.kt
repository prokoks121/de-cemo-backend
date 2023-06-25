package decemo.com.events.explorer.mapper

import decemo.com.bardatastore.entity.Event
import decemo.com.events.explorer.model.dto.EventDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface EventMapper {
    fun mapToDto(event: Event): EventDto
    fun mapToDto(events: MutableList<Event>): MutableList<EventDto>
}