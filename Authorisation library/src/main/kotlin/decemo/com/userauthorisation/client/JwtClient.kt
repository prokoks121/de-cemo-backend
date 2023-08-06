package decemo.com.userauthorisation.client

import decemo.com.userauthorisation.configuration.JwtClientConfiguration
import decemo.com.userauthorisation.model.JwtToken
import decemo.com.userauthorisation.model.JwtTokenBody
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class JwtClient(val restTemplate: RestTemplate, val configuration: JwtClientConfiguration) {

    companion object {
        const val MIDDLE_PATH = "/api/v1/jwt"
        const val ENCODE_PATH = "/encode"
        const val DECODE_PATH = "/decode"
        const val REFRESH_PATH = "/refresh"
    }

    fun encode(userId: Long, role: String?): Result<JwtToken> {
        val tokenBody = JwtTokenBody(userId, role)
        return encode(tokenBody)
    }

    fun encode(tokenBody: JwtTokenBody): Result<JwtToken> {
        return runCatching {
            val response = postRequest(JwtToken::class.java, tokenBody, ENCODE_PATH)
            return response.validateResponse()
        }
    }

    fun decode(token: String): Result<JwtTokenBody> {
        return runCatching {
            val response = postRequest(JwtTokenBody::class.java, token, DECODE_PATH)
            return response.validateResponse()
        }
    }

    fun refreshToken(refreshToken: String): Result<JwtToken> {
        return runCatching {
            val response = postRequest(JwtToken::class.java, refreshToken, REFRESH_PATH)
            return response.validateResponse()
        }
    }

    fun <T> ResponseEntity<T>.validateResponse(): Result<T> {
        if (statusCode == HttpStatus.OK) {
            if (body == null) {
                return Result.failure(NullPointerException("Authorisation server return null."))
            }
            return Result.success(body!!)
        }
        return Result.failure(NullPointerException("Authorisation server return status $statusCode"))
    }

    private fun <T> buildEntity(body: T): HttpEntity<T> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.accept = Collections.singletonList(MediaType.APPLICATION_JSON)
        return HttpEntity(body, headers)
    }

    private fun <T, R> postRequest(clazz: Class<R>, body: T, path: String): ResponseEntity<R> {
        val entity = buildEntity(body)
        val url = "${configuration.baseUrl}$MIDDLE_PATH$path"
        return restTemplate.postForEntity(url, entity, clazz)
    }
}