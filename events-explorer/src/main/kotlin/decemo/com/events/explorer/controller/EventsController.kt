package decemo.com.events.explorer.controller

import decemo.com.events.explorer.facade.EventFacade
import decemo.com.events.explorer.model.EventRequest
import decemo.com.events.explorer.model.dto.BarEvent
import decemo.com.events.explorer.model.dto.EventDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1")
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

    @PostMapping("/events/add")
    fun createNewEvent(@RequestBody eventRequest: EventRequest): ResponseEntity<EventDto> {
        eventFacade.addNewEvent(eventRequest).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, it.message)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}