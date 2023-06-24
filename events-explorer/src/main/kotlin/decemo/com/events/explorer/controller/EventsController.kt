package decemo.com.events.explorer.controller

import decemo.com.bardatastore.service.DataStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventsController(val dataStore:DataStoreService) {

    @GetMapping
    fun test(){
        println(dataStore.test())
    }
}