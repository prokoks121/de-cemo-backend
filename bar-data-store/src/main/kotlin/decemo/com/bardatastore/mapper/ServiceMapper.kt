package decemo.com.bardatastore.mapper

import decemo.com.bardatastore.entity.Service
import decemo.com.bardatastore.model.ServiceDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ServiceMapper {
    fun mapServiceToDto(service: Service): ServiceDto
}