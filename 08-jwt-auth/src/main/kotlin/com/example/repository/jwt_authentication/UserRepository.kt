package com.example.repository.jwt_authentication

import com.example.model.jwt_authentication.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User?, Long?> {
    @Query("SELECT u FROM User u WHERE u.userName =: userName")
    fun findByUsername(@Param("userName") userName: String?) : User?
}