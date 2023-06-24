package decemo.com.barexplorer.mapper

import decemo.com.barexplorer.entity.Bar
import decemo.com.barexplorer.model.BarDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarMapper {
    fun mapBarToDto(bar: Bar): BarDto
    fun mapBarsToDto(bars: List<Bar>): List<BarDto>
}