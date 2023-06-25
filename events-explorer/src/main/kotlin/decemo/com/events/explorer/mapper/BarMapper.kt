package decemo.com.events.explorer.mapper

import decemo.com.bardatastore.entity.Bar
import decemo.com.events.explorer.model.dto.BarDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarMapper {
    fun mapToDto(bar: Bar): BarDto
    fun mapToDto(bars: MutableList<Bar>): MutableList<BarDto>
}