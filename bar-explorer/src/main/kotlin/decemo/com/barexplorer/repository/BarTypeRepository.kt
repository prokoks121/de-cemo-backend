package decemo.com.barexplorer.repository

import decemo.com.barexplorer.entity.BarType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BarTypeRepository : JpaRepository<BarType, Long> {

    fun findBarTypeByType(type: String): Optional<BarType>

    fun findAllByIdIn(ids: List<Long>): List<BarType>
}