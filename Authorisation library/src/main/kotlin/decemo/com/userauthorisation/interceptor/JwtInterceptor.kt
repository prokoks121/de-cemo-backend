package decemo.com.userauthorisation.interceptor

import decemo.com.userauthorisation.client.JwtClient
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtInterceptor(private val jwtClient: JwtClient) : HandlerInterceptor {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER = "Bearer "
        const val TOKEN_BODY = "tokenBody"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        try {
            val oAuthToken = request.getHeader(AUTHORIZATION_HEADER)
            val token = oAuthToken.removePrefix(BEARER)
            val result = jwtClient.decode(token)
            result.onSuccess { tokenBody ->
                request.setAttribute(TOKEN_BODY, tokenBody)
            }.onFailure {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), it.message)
                return false
            }
        } catch (e: Exception) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.message)
            return false
        }
        return true
    }
}