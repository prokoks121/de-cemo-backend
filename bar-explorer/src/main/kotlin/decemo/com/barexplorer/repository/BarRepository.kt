package decemo.com.barexplorer.repository

import decemo.com.barexplorer.entity.Bar
import decemo.com.barexplorer.entity.BarType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BarRepository : JpaRepository<Bar, Long> {
    fun findAllByTypeIn(barTypes: List<BarType>): List<Bar>
}