package decemo.com.user.service

import decemo.com.user.exception.WrongCredentialsException
import decemo.com.user.model.entity.User
import decemo.com.user.repository.UserRepository
import decemo.com.user.toLibrary.client.JwtClient
import decemo.com.user.toLibrary.model.JwtToken
import org.springframework.stereotype.Service

@Service
class RegistrationService(val client: JwtClient, val userRepository: UserRepository) {

    fun registerNewClient(newUser: User): Result<JwtToken> {
        val user = userRepository.save(newUser)
        return client.encode(user.id, user.role?.name)
    }

    fun loginClient(email: String, password: String): Result<JwtToken> {
        val user = userRepository.findUserByEmailAndPassword(email, password)
        if (user.isPresent) {
            return client.encode(user.get().id, user.get().role?.name)
        }
        return Result.failure(WrongCredentialsException("User not exists with provided credentials"))
    }
}