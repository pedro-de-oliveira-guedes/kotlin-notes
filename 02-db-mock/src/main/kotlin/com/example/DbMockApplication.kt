package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DbMockApplication

fun main(args: Array<String>) {
	runApplication<DbMockApplication>(*args)
}
