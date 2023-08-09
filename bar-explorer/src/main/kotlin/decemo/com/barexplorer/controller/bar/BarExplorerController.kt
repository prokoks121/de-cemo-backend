package decemo.com.barexplorer.controller.bar

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
}