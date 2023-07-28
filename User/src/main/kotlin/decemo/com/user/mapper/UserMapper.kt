package decemo.com.user.mapper

import decemo.com.user.model.dto.UserDto
import decemo.com.user.model.entity.User
import decemo.com.user.model.request.UserRegistrationBody

fun UserRegistrationBody.mapTo(): User {
    val user = User()
    user.fullName = fullName
    user.email = email
    user.password = password
    return user
}

fun User.mapToUserDto(): UserDto {
    return UserDto(
        id,
        email,
        createdAt,
        fullName,
        role?.name
    )
}