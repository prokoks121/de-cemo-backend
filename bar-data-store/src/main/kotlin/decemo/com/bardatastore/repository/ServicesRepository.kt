package decemo.com.bardatastore.repository

import decemo.com.bardatastore.entity.Service
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ServicesRepository : JpaRepository<Service, Long> {
    fun findAllByNameIn(serviceNames: MutableList<String>): MutableList<Service>
    fun findServicesByName(serviceName:String): Optional<Service>
}