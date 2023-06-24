package decemo.com.bardatastore.service

import decemo.com.bardatastore.repository.BarRepository
import decemo.com.bardatastore.repository.BarTypeRepository
import decemo.com.bardatastore.repository.ServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DataStoreService {
    @Autowired
    lateinit var barRepository: BarRepository

    @Autowired
    lateinit var barTypeRepository: BarTypeRepository

    @Autowired
    lateinit var servicesRepository: ServicesRepository

    fun test(){
        barRepository.findAll()
    }
}