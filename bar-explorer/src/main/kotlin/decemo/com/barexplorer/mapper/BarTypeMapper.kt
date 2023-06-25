package decemo.com.barexplorer.mapper

import decemo.com.bardatastore.entity.BarType
import decemo.com.barexplorer.model.BarTypeDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarTypeMapper {
    fun mapToDto(barTypes: BarType): BarTypeDto
    fun mapToDto(barTypes: MutableList<BarType>): MutableList<BarTypeDto>
}