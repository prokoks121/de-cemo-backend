package decemo.com.bardatastore.entity

import javax.persistence.*

@Entity
class Event(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    val imageUrl: String,
    @ManyToOne
    @JoinColumn(name = "bar_id")
    val bar: Bar
)