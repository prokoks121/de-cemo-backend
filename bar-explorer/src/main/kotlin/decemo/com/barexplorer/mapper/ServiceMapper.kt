package decemo.com.barexplorer.mapper

import decemo.com.barexplorer.entity.Service
import decemo.com.barexplorer.model.ServiceDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ServiceMapper {
    fun mapServiceToDto(service: Service): ServiceDto
}