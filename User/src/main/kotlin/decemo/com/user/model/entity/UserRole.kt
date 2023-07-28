package decemo.com.user.model.entity

import javax.persistence.*

@Entity
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0
    lateinit var name: String

    @OneToMany
    val users: MutableList<User> = mutableListOf()
}