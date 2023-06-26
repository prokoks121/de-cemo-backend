package decemo.com.bardatastore.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Event(
    @Id
    @GeneratedValue
    val id: Long = 0,
    var name: String,
    var imageUrl: String,
    @ManyToOne
    @JoinColumn(name = "bar_id")
    var bar: Bar,
    var createdAt: ZonedDateTime
)