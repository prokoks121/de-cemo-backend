package decemo.com.barexplorer.facade

import decemo.com.bardatastore.entity.Bar
import decemo.com.bardatastore.entity.BarType
import decemo.com.bardatastore.entity.Service
import decemo.com.bardatastore.repository.BarRepository
import decemo.com.bardatastore.repository.BarTypeRepository
import decemo.com.bardatastore.repository.ServicesRepository
import decemo.com.barexplorer.mapper.BarMapper
import decemo.com.barexplorer.mapper.BarTypeMapper
import decemo.com.barexplorer.model.BarDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BarFacade {
    @Autowired
    lateinit var barRepository: BarRepository

    @Autowired
    lateinit var barTypeRepository: BarTypeRepository

    @Autowired
    lateinit var servicesRepository: ServicesRepository

    @Autowired
    lateinit var barMapper: BarMapper

    @Autowired
    lateinit var barTypeMapper: BarTypeMapper

    fun getAllBars(): List<BarDto> {
        return barMapper.mapToDto(barRepository.findAll())
    }

    fun getAllBarsByType(barTypeIds: MutableList<Long>): MutableList<BarDto> {
        val barTypeList = barTypeRepository.findAllByIdIn(barTypeIds)
        val bars = barRepository.findAllByBarTypeIn(barTypeList)
        return barMapper.mapToDto(bars)
    }

    fun getBarById(barId: Long): BarDto? {
        val bar = barRepository.findById(barId).orElse(null)
        return if (bar == null) {
            null
        } else {
            barMapper.mapToDto(bar)
        }
    }

    fun addBar(barDto: BarDto): Result<BarDto> {
        return runCatching {
            val barType = barTypeRepository.findBarTypeByType(barDto.barType.type).orElse(BarType(bars = arrayListOf(), type = barDto.barType.type))
            val services = barDto.services.map {
                servicesRepository.findServicesByName(it.name).orElse(Service(name = it.name, iconUrl = "", bars = mutableListOf()))
            }
            val bar = Bar(
                name = barDto.name,
                address = barDto.address,
                latitude = barDto.latitude.toDouble(),
                longitude = barDto.longitude.toDouble(),
                phoneNumber = barDto.phoneNumber,
                mainPictureUrl = barDto.mainPictureUrl,
                galleryPictureUrls = barDto.galleryPictureUrls,
                barType = barType,
                workTime = barDto.workTime,
                services = services.toMutableList()
            )
            barMapper.mapToDto(barRepository.save(bar))
        }
    }

    fun getAllBarsByPartialName(partialName: String): MutableList<BarDto> {
        val bars = barRepository.findAllByNameContaining(partialName)
        return barMapper.mapToDto(bars)
    }
}