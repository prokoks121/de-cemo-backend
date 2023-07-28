package decemo.com.user.controller

import decemo.com.user.model.dto.UserDto
import decemo.com.user.service.UserService
import decemo.com.user.toLibrary.model.JwtTokenBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/get")
    fun getUser(@RequestAttribute tokenBody:JwtTokenBody):ResponseEntity<UserDto>{
        userService.getUser(tokenBody.id).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

}