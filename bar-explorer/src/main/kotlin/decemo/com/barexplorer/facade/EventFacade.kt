package decemo.com.barexplorer.facade


import decemo.com.bardatastore.entity.Event
import decemo.com.bardatastore.repository.BarRepository
import decemo.com.bardatastore.repository.BarTypeRepository
import decemo.com.bardatastore.repository.EventRepository
import decemo.com.bardatastore.repository.ServicesRepository
import decemo.com.barexplorer.mapper.BarMapper
import decemo.com.barexplorer.mapper.EventMapper
import decemo.com.barexplorer.model.BarEvent
import decemo.com.barexplorer.model.EventDto
import decemo.com.barexplorer.model.EventRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.time.ZonedDateTime

@Component
class EventFacade {

    @Autowired
    private lateinit var barRepository: BarRepository

    @Autowired
    private lateinit var eventRepository: EventRepository

    @Autowired
    private lateinit var eventMapper: EventMapper

    @Autowired
    private lateinit var barMapper: BarMapper

    fun getAllEvents(): MutableList<BarEvent> {
        val barEvents = barRepository.findAllByEventsIsNotEmpty()
        return barMapper.mapToBarEvents(barEvents)
    }

    fun getAllEventsByBar(barId: Long): MutableList<EventDto> {
        val events = eventRepository.findEventsByBar_Id(barId)
        return eventMapper.mapToDto(events)
    }

    fun addNewEvent(eventRequest: EventRequest): Result<EventDto> {
        runCatching {
            val bar = barRepository.findById(eventRequest.barId)
            if (bar.isEmpty) {
                return Result.failure(ResponseStatusException(HttpStatus.NOT_FOUND, "Bar with id: ${eventRequest.barId} doesn't exists."))
            }
            val event = Event(name = eventRequest.name, imageUrl = eventRequest.imageUrl, bar = bar.get(), createdAt = ZonedDateTime.now())
            bar.get().events.add(event)
            barRepository.save(bar.get())
            return Result.success(eventMapper.mapToDto(eventRepository.save(event)))
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    fun addNewEvent(eventRequest: List<EventRequest>): Result<List<EventDto>> {
        return runCatching { eventRequest.map { addNewEvent(it).getOrThrow() } }
    }
}