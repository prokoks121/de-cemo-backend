package decemo.com.user.authorization.controller

import decemo.com.user.authorization.controller.model.EncodedToken
import decemo.com.user.authorization.controller.model.TokenBody
import decemo.com.user.authorization.jwt.model.JwtToken
import decemo.com.user.authorization.service.JwtAuthorization
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*

@RestController
class Controller(val jwtAuthorization: JwtAuthorization) {

    @PostMapping("/encode")
    fun encode(@RequestBody tokenBody: TokenBody): ResponseEntity<EncodedToken> {
        val token = jwtAuthorization.encodeWithRefreshToken(tokenBody.id)
        val encodedToken = EncodedToken(token.first, token.second, Date.from(Instant.now()))
        return ResponseEntity.ok(encodedToken)
    }

    @PostMapping("/decode")
    fun decode(@RequestBody token: String): ResponseEntity<JwtToken> {
        val tokenBody = jwtAuthorization.decodeToken(token)
        tokenBody.onSuccess {
            return ResponseEntity.ok(it)
        }.onFailure {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, it.message, it)
        }
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }

    @PostMapping("/refresh")
    fun refresh() {

    }
}