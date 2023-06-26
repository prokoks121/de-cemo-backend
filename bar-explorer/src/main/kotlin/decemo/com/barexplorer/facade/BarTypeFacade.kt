package decemo.com.barexplorer.facade

import decemo.com.bardatastore.repository.BarTypeRepository
import decemo.com.barexplorer.mapper.BarTypeMapper
import decemo.com.barexplorer.model.BarTypeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BarTypeFacade {
    @Autowired
    lateinit var barTypeRepository: BarTypeRepository

    @Autowired
    lateinit var barTypeMapper: BarTypeMapper

    fun getAllBarTypes(): MutableList<BarTypeDto> {
        val barTypes = barTypeRepository.findAll()
        return barTypeMapper.mapToDto(barTypes)
    }
}