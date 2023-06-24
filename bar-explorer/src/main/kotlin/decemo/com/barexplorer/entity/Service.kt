package decemo.com.barexplorer.entity

import javax.persistence.*

@Entity
class Service(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var name: String,
    var iconUrl: String,
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "services")
    var bars: MutableList<Bar> = mutableListOf()
)