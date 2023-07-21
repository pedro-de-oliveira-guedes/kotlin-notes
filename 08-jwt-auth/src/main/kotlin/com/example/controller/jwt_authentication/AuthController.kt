package com.example.controller.jwt_authentication

import com.example.data.vo.v1.jwt_authentication.AccountCredentialsVO
import com.example.services.jwt_authentication.AuthService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Authentication endpoint")
@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    lateinit var authService: AuthService

    fun signin(@RequestBody data: AccountCredentialsVO?) : ResponseEntity<*> {
        return (
            if (data!!.username.isNullOrBlank() || data.password.isNullOrBlank())
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
            else
                authService.signin(data)
        )
    }

}