package decemo.com.barexplorer.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class BarType(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var type: String,
    @OneToMany(mappedBy = "type")
    var bars: MutableList<Bar> = mutableListOf()
)