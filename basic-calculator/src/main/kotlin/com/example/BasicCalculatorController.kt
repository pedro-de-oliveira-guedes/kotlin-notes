package com.example

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicCalculatorController {
    val validator = Validator()

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    fun sum(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        return if (numberOneDouble != null && numberTwoDouble != null)
            numberOneDouble + numberTwoDouble
        else
            throw Exception("Um dos parâmetros informados não é um número.")
    }
}