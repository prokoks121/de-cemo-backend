package decemo.com.barexplorer.repository

import decemo.com.barexplorer.entity.Service
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ServicesRepository : JpaRepository<Service, Long> {
    fun findAllByNameIn(serviceNames: Set<String>): Set<Service>
    fun findServicesByName(serviceName:String): Optional<Service>
}