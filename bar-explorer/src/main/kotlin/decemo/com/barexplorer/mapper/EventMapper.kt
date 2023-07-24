package decemo.com.barexplorer.mapper

import decemo.com.bardatastore.entity.Event
import decemo.com.barexplorer.model.EventDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface EventMapper {
    fun mapToDto(event: Event): EventDto
    fun mapToDto(events: MutableList<Event>): MutableList<EventDto>
}