package decemo.com.bardatastore.mapper

import decemo.com.bardatastore.entity.Bar
import decemo.com.bardatastore.model.BarDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarMapper {
    fun mapToDto(bar: Bar): BarDto
    fun mapToDto(bars: List<Bar>): List<BarDto>
    fun mapToEntity(barDto: BarDto): Bar
    fun mapToEntity(barsDto: List<BarDto>): List<Bar>
}