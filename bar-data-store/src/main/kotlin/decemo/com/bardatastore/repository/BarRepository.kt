package decemo.com.bardatastore.repository

import decemo.com.bardatastore.entity.Bar
import decemo.com.bardatastore.entity.BarType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BarRepository : JpaRepository<Bar, Long> {
    fun findAllByBarTypeIn(barTypes: MutableList<BarType>): MutableList<Bar>
    fun findAllByBarType_IdIn(barTypes: MutableList<BarType>): MutableList<Bar>
    fun findAllByNameContainingIgnoreCase(partialName: String): MutableList<Bar>
    fun findAllByEventsIsNotEmpty(): MutableList<Bar>
}