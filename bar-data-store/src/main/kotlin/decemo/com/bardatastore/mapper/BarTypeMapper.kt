package decemo.com.bardatastore.mapper

import decemo.com.bardatastore.entity.BarType
import decemo.com.bardatastore.model.BarTypeDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarTypeMapper {
    fun mapBarTypeToDto(barType: BarType): BarTypeDto
}