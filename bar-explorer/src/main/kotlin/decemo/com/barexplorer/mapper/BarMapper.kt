package decemo.com.barexplorer.mapper

import decemo.com.bardatastore.entity.Bar
import decemo.com.barexplorer.model.BarDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarMapper {
    fun mapToDto(bar: Bar): BarDto
    fun mapToDto(bars: MutableList<Bar>): MutableList<BarDto>
}