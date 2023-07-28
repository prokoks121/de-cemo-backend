package decemo.com.user.model.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "decemo_user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    lateinit var fullName: String

    @Column(unique = true)
    lateinit var email: String
    lateinit var password: String
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    @ManyToOne
    val role: UserRole? = null
}
