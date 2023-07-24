package decemo.com.events.explorer.mapper

import decemo.com.bardatastore.entity.Bar
import decemo.com.events.explorer.model.dto.BarDto
import decemo.com.events.explorer.model.dto.BarEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface BarMapper {
    fun mapToDto(bar: Bar): BarDto
    fun mapToDto(bars: MutableList<Bar>): MutableList<BarDto>

    @Mapping(target = "barId", source = "id")
    @Mapping(target = "barName", source = "name")
    @Mapping(target = "barImageUrl", source = "mainPictureUrl")
    fun mapToBarEvent(bar: Bar): BarEvent
    fun mapToBarEvents(bars: MutableList<Bar>): MutableList<BarEvent>
}