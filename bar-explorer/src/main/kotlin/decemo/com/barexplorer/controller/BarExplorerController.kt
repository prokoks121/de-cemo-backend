package decemo.com.barexplorer.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import decemo.com.barexplorer.entity.Bar
import decemo.com.barexplorer.entity.BarType
import decemo.com.barexplorer.entity.Service
import decemo.com.barexplorer.mapper.BarMapper
import decemo.com.barexplorer.model.BarDto
import decemo.com.barexplorer.repository.BarRepository
import decemo.com.barexplorer.repository.BarTypeRepository
import decemo.com.barexplorer.repository.ServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class BarExplorerController {

    @Autowired
    lateinit var barRepository: BarRepository

    @Autowired
    lateinit var barTypeRepository: BarTypeRepository

    @Autowired
    lateinit var servicesRepository: ServicesRepository

    @Autowired
    lateinit var barMapper: BarMapper

    @GetMapping("/bars")
    fun getFilteredBaryByType(@RequestParam(required = false) bartTypes: List<Long>?): List<BarDto> {
        if (bartTypes == null) {
            val bars = barRepository.findAll()
            return barMapper.mapBarsToDto(bars)
        }

        val barTypeList = barTypeRepository.findAllByIdIn(bartTypes)
        val bars = barRepository.findAllByTypeIn(barTypeList)
        return barMapper.mapBarsToDto(bars)
    }

    @GetMapping("/bar/{id}")
    fun getBar(@PathVariable id: Long): ResponseEntity<BarDto> {
        val bar = barRepository.findById(id)
        return if (bar.isPresent) {
            ResponseEntity.ok(barMapper.mapBarToDto(bar.get()))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @PostMapping("/add")
    fun add(@RequestBody models: List<ModelToAdd>) {

        models.forEach { model ->
            var barType = barTypeRepository.findBarTypeByType(model.vrsta).orElse(BarType(bars = arrayListOf(), type = model.vrsta))
            var services = arrayListOf<Service>()
            model.usluge.forEach {
                services.add(servicesRepository.findServicesByName(it).orElse(Service(name = it, iconUrl = "", bars = mutableListOf())))
            }

            val bar = Bar(
                name = model.ime,
                address = model.adresa,
                latitude = model.lat,
                longitude = model.long,
                phoneNumber = model.telefon,
                mainPictureUrl = model.slika,
                galleryPictureUrls = jacksonObjectMapper().writeValueAsString(model.galerija),
                type = barType,
                workTime = jacksonObjectMapper().writeValueAsString(model.radno),
                services = services
            )
            barRepository.save(bar)
        }
    }
}

data class ModelToAdd(
    val adresa: String,
    val galerija: ArrayList<String>,
    val ime: String,
    val lat: String,
    val long: String,
    val radno: ArrayList<String>,
    val slika: String,
    val telefon: String,
    val usluge: ArrayList<String>,
    val vrsta: String
)