package decemo.com.user.repository

import decemo.com.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByEmailAndPassword(email: String, password: String): Optional<User>
}