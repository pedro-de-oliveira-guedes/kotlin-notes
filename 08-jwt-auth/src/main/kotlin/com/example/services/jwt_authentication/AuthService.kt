package com.example.services.jwt_authentication

import com.example.data.vo.v1.jwt_authentication.AccountCredentialsVO
import com.example.data.vo.v1.jwt_authentication.TokenVO
import com.example.repository.jwt_authentication.UserRepository
import com.example.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthService {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var repository: UserRepository

    private val logger = Logger.getLogger(AuthService::class.java.name)

    fun signin(data: AccountCredentialsVO) : ResponseEntity<*> {
        logger.info("User ${data.username} trying to login")

        return try {
            val username = data.username
            val password = data.password

            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            val user = repository.findByUsername(username)
            val tokenResponse : TokenVO = if (user != null) {
                tokenProvider.createAccessToken(username!!, user.roles)
            } else {
                throw  UsernameNotFoundException("Username $username not found.")
            }

            ResponseEntity.ok(tokenResponse)
        } catch(exc: Exception) {
            throw  BadCredentialsException("Invalid username or password received. " + exc.message)
        }
    }

    fun refreshToken(username: String, refreshToken: String) : ResponseEntity<*> {
        logger.info("User ${username} is trying to refresh their token.")

        val user = repository.findByUsername(username)

        val tokenResponse: TokenVO = if (user != null)
            tokenProvider.refreshToken(refreshToken)
        else
            throw UsernameNotFoundException("Username $username not found.")

        return ResponseEntity.ok(tokenResponse)
    }
}