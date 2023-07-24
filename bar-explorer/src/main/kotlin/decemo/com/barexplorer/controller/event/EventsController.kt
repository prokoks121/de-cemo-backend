package decemo.com.barexplorer.controller.event

import decemo.com.barexplorer.facade.EventFacade
import decemo.com.barexplorer.model.BarEvent
import decemo.com.barexplorer.model.EventDto
import decemo.com.barexplorer.model.EventRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class EventsController {
    @Autowired
    private lateinit var eventFacade: EventFacade

    @GetMapping("/events")
    fun getAllEvents(): MutableList<BarEvent> {
        return eventFacade.getAllEvents()
    }

    @GetMapping("/events/bar/{barId}")
    fun getAllEventsByBar(@PathVariable barId: Long): MutableList<EventDto> {
        return eventFacade.getAllEventsByBar(barId)
    }

    @PostMapping("/event/add")
    fun createNewEvent(@RequestBody eventRequest: EventRequest): ResponseEntity<EventDto> {
        eventFacade.addNewEvent(eventRequest).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, it.message)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/events/add")
    fun createNewEvent(@RequestBody eventRequest: List<EventRequest>): ResponseEntity<List<EventDto>> {
        eventFacade.addNewEvent(eventRequest).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, it.message)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}