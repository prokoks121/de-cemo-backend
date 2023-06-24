package decemo.com.bardatastore.repository

import decemo.com.bardatastore.entity.Bar
import decemo.com.bardatastore.entity.BarType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BarRepository : JpaRepository<Bar, Long> {
    fun findAllByTypeIn(barTypes: List<BarType>): List<Bar>
}