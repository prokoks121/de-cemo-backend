package decemo.com.user.service

import decemo.com.user.exception.UserNotExistsException
import decemo.com.user.mapper.mapToUserDto
import decemo.com.user.model.dto.UserDto
import decemo.com.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {

    fun getUser(userId: Long): Result<UserDto> {
        val user = repository.findById(userId)
        if (user.isPresent) {
            return Result.success(user.get().mapToUserDto())
        }
        return Result.failure(UserNotExistsException("User with id $userId not exists"))
    }
}