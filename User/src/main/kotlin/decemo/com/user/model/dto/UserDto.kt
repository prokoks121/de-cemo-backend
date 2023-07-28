package decemo.com.user.model.dto

import java.time.ZonedDateTime


data class UserDto(
    val id: Long,
    val email: String,
    val createdAt: ZonedDateTime,
    val fullName: String,
    val role: String?
)