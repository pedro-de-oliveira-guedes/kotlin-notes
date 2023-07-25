package com.example.services.jwt_authentication

import com.example.controller.BookController
import com.example.data.vo.v1.BookVO
import com.example.exceptions.NullObjectException
import com.example.exceptions.ResourceNotFoundException
import com.example.mapper.DozerMapper
import com.example.model.Book
import com.example.repository.BookRepository
import com.example.repository.jwt_authentication.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Service
class UserService(@field:Autowired var repository: UserRepository) : UserDetailsService {

    private val logger = Logger.getLogger(UserService::class.java.name);

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Finding one User by Username ${username}")

        val user = repository.findByUsername(username)

        return user ?: throw UsernameNotFoundException("Username ${username} not found.")
    }
}