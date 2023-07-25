package com.example.util

import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

class PasswordEncoder {
    fun encodePassword(password: String) : String {
        val pbkdf2Encoder = Pbkdf2PasswordEncoder("", 8, 185_000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)

        val encoders : MutableMap<String, PasswordEncoder> = HashMap()
        encoders["pbkdf2"] = pbkdf2Encoder

        val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)

        val encodedPassword = passwordEncoder.encode(password)

        return encodedPassword.split("}")[1]
    }
}