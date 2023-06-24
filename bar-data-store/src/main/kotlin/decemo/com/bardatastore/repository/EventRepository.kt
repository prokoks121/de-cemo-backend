package decemo.com.bardatastore.repository

import decemo.com.bardatastore.entity.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository :JpaRepository<Event, Long> {

}