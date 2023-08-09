package decemo.com.user.controller

import decemo.com.user.mapper.mapTo
import decemo.com.user.model.request.LoginRequestBody
import decemo.com.user.model.request.UserRegistrationBody
import decemo.com.user.service.LoginService
import decemo.com.userauthorisation.model.JwtToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class LoginController(val registrationService: LoginService) {

    @PostMapping("/login")
    fun loginUser(@RequestBody loginRequestBody: LoginRequestBody): ResponseEntity<JwtToken> {
        registrationService.loginClient(loginRequestBody.email, loginRequestBody.password).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }

    @PostMapping("/registration")
    fun userRegistration(@RequestBody userRegistrationBody: UserRegistrationBody): ResponseEntity<JwtToken> {
        registrationService.registerNewClient(userRegistrationBody.mapTo()).onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.CONFLICT)
    }
}