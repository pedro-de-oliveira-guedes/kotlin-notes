package com.example

import com.example.util.PasswordEncoder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Startup

fun main(args: Array<String>) {
	runApplication<Startup>(*args)
//	println(PasswordEncoder().encodePassword("pedro123"))
}
