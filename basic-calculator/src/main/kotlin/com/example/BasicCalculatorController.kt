package com.example

import com.example.exceptions.BadRequestExceptions
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.validations.Validator

@RestController
class BasicCalculatorController {
    val validator = Validator()

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    fun sum(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return numberOneDouble + numberTwoDouble
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    fun sub(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return numberOneDouble - numberTwoDouble
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/mul/{numberOne}/{numberTwo}")
    fun mul(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return numberOneDouble * numberTwoDouble
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    fun div(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return numberOneDouble / numberTwoDouble
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/avg/{numberOne}/{numberTwo}")
    fun avg(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return (numberOneDouble + numberTwoDouble) / 2
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/sqrt/{numberOne}")
    fun sqrt(@PathVariable numberOne: String,): Double {
        val numberOneDouble = validator.isNumber(numberOne)

        if (numberOneDouble != null)
            return kotlin.math.sqrt(numberOneDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }
}