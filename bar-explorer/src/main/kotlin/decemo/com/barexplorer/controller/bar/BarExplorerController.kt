package decemo.com.barexplorer.controller.bar

import decemo.com.bardatastore.entity.Bar
import decemo.com.bardatastore.entity.BarType
import decemo.com.bardatastore.entity.Service
import decemo.com.bardatastore.repository.BarRepository
import decemo.com.bardatastore.repository.BarTypeRepository
import decemo.com.bardatastore.repository.ServicesRepository
import decemo.com.barexplorer.facade.BarFacade
import decemo.com.barexplorer.model.BarDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class BarExplorerController {
    @Autowired
    lateinit var barRepository: BarRepository

    @Autowired
    lateinit var barTypeRepository: BarTypeRepository

    @Autowired
    lateinit var servicesRepository: ServicesRepository

    @Autowired
    lateinit var facade: BarFacade

    @GetMapping("/bars")
    fun getBar(@RequestParam(required = false) bartTypes: MutableList<Long>?): List<BarDto> {
        if (bartTypes == null) {
            return facade.getAllBars()
        }
        return facade.getAllBarsByType(bartTypes)
    }

    @GetMapping("/bar/{id}")
    fun getBar(@PathVariable id: Long): ResponseEntity<BarDto> {
        val bar = facade.getBarById(id)
        return if (bar != null) {
            ResponseEntity.ok(bar)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/bar/find/{name}")
    fun findBarByName(@PathVariable name: String): MutableList<BarDto> {
        return facade.getAllBarsByPartialName(name)
    }

    @PostMapping("/bar/add")
    fun createBar(@RequestBody barDto: BarDto): ResponseEntity<BarDto> {
        facade.addBar(barDto).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, it.message, it.cause)
        }
        throw ResponseStatusException(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("/add")
    fun add(@RequestBody models: List<ModelToAdd>) {

        models.forEach { model ->
            var barType = barTypeRepository.findBarTypeByType(model.vrsta).orElse(BarType(bars = arrayListOf(), type = model.vrsta, iconUrl = ""))
            var services = arrayListOf<Service>()
            model.usluge.forEach {
                services.add(servicesRepository.findServicesByName(it).orElse(Service(name = it, iconUrl = "", bars = mutableListOf())))
            }

            val bar = Bar(
                name = model.ime,
                address = model.adresa,
                latitude = model.lat.toDouble(),
                longitude = model.long.toDouble(),
                phoneNumber = model.telefon,
                mainPictureUrl = model.slika,
                galleryPictureUrls = model.galerija,
                barType = barType,
                workTime = model.radno,
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