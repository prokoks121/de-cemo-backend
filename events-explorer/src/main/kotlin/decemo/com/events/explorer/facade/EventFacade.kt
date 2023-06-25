package decemo.com.events.explorer.facade


import decemo.com.bardatastore.entity.Event
import decemo.com.bardatastore.repository.BarRepository
import decemo.com.bardatastore.repository.BarTypeRepository
import decemo.com.bardatastore.repository.EventRepository
import decemo.com.bardatastore.repository.ServicesRepository
import decemo.com.events.explorer.mapper.EventMapper
import decemo.com.events.explorer.model.EventRequest
import decemo.com.events.explorer.model.dto.EventDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class EventFacade {

    @Autowired
    private lateinit var barRepository: BarRepository

    @Autowired
    private lateinit var barTypeRepository: BarTypeRepository

    @Autowired
    private lateinit var servicesRepository: ServicesRepository

    @Autowired
    private lateinit var eventRepository: EventRepository

    @Autowired
    private lateinit var eventMapper: EventMapper

    fun getAllEvents(): MutableList<EventDto> {
        val events = eventRepository.findAll()
        return eventMapper.mapToDto(events)
    }

    fun getAllEventsByBar(barId: Long): MutableList<EventDto> {
        val events = eventRepository.findEventsByBar_Id(barId)
        return eventMapper.mapToDto(events)
    }

    fun addNewEvent(eventRequest: EventRequest): Result<EventDto> {
        val bar = barRepository.findById(eventRequest.barId)
        if (bar.isEmpty) {
            return Result.failure(ResponseStatusException(HttpStatus.NOT_FOUND, "Bar with id: ${eventRequest.barId} doesn't exists."))
        }
        val event = Event(name = eventRequest.name, imageUrl = eventRequest.imageUrl, bar = bar.get())
        return Result.success(eventMapper.mapToDto(eventRepository.save(event)))
    }

}