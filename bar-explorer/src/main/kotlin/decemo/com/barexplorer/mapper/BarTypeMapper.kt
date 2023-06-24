package decemo.com.barexplorer.mapper

import decemo.com.barexplorer.entity.BarType
import decemo.com.barexplorer.model.BarTypeDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarTypeMapper {
    fun mapBarTypeToDto(barType: BarType): BarTypeDto
}