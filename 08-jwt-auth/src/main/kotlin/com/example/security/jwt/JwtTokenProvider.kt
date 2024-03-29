package com.example.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.data.vo.v1.jwt_authentication.TokenVO
import com.example.exceptions.InvalidJwtAuthenticationException
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Base64
import java.util.Date

@Suppress("UNUSED_EXPRESSION")
@Service
class JwtTokenProvider {
    @Value("\${security.jwt.token.secret-key:secret}")
    private var secretKey = "secret"

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityMS: Long = 3_600_000 // 1 hora

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(username: String, roles: List<String?>,) : TokenVO {
        val now = Date()
        val validity = Date(now.time + validityMS,)
        val accessToken = getAccessToken(username, roles, now, validity,)
        val refreshToken = getRefreshToken(username, roles, now,)

        return TokenVO(
            username = username,
            authenticated =  true,
            accessToken = accessToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity,
        )
    }

    fun refreshToken(refreshToken : String) : TokenVO {
        var token: String = ""
        if (refreshToken.contains("Bearer"))
            token = refreshToken.substring("Bearer ".length)

        val verifier: JWTVerifier = JWT.require(algorithm).build()
        val decodedJWT: DecodedJWT = verifier.verify(token)

        val username: String = decodedJWT.subject
        val roles: List<String> = decodedJWT.getClaim("roles").asList(String::class.java)

        return createAccessToken(username, roles)
    }

    private fun getAccessToken(username: String, roles: List<String?>, now: Date, validity: Date): String {
        val issuerURL: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()

        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(username)
            .withIssuer(issuerURL)
            .sign(algorithm)
            .trim()
    }

    private fun getRefreshToken(username: String, roles: List<String?>, now: Date): String {
        val validityRefreshToken = Date(now.time + validityMS * 3)

        return JWT.create()
            .withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(username)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token: String) : Authentication {
        val decodedJWT : DecodedJWT = decodedToken(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(decodedJWT.subject)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun decodedToken(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier : JWTVerifier = JWT.require(algorithm).build()

        return verifier.verify(token)
    }

    fun resolveToken(req: HttpServletRequest) : String? {
        val bearerToken = req.getHeader("Authorization")

        return if(!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer")) {
            bearerToken.substring("Bearer ".length)
        } else null
    }

    fun validateToken(token: String) : Boolean {
        val decodedJWT = decodedToken(token)

        try {
            if ( decodedJWT.expiresAt.before(Date()) ) false
            return true
        } catch (exc: Exception) {
            throw  InvalidJwtAuthenticationException("Expired or invalid JWT Token")
        }
    }
}