package decemo.com.bardatastore.repository

import decemo.com.bardatastore.entity.BarType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BarTypeRepository : JpaRepository<BarType, Long> {

    fun findBarTypeByType(type: String): Optional<BarType>

    fun findAllByIdIn(ids: MutableList<Long>): MutableList<BarType>
}