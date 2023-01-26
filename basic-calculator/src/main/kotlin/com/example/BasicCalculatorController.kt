package com.example

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicCalculatorController {
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    fun sum(@PathVariable numberOne: String,
            @PathVariable numberTwo: String): String {
        numberOne.toDouble()
        return "Howdy!"
    }
}