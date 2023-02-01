package com.example

import com.example.exceptions.BadRequestExceptions
import com.example.mathOperations.mathOperations
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.validations.Validator
import kotlin.math.pow

@RestController
class BasicCalculatorController {
    val validator = Validator()

    val mathOperations = mathOperations()

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    fun sum(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return mathOperations.sum(numberOneDouble, numberTwoDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    fun sub(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return mathOperations.sub(numberOneDouble, numberTwoDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/mul/{numberOne}/{numberTwo}")
    fun mul(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return mathOperations.mul(numberOneDouble, numberTwoDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    fun div(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return mathOperations.div(numberOneDouble, numberTwoDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/avg/{numberOne}/{numberTwo}")
    fun avg(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return mathOperations.avg(numberOneDouble, numberTwoDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/sqrt/{numberOne}")
    fun sqrt(@PathVariable numberOne: String,): Double {
        val numberOneDouble = validator.isNumber(numberOne)

        if (numberOneDouble != null)
            return mathOperations.sqrt(numberOneDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }

    @RequestMapping("/power/{numberOne}/{numberTwo}")
    fun power(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        val numberOneDouble = validator.isNumber(numberOne)
        val numberTwoDouble = validator.isNumber(numberTwo)

        if (numberOneDouble != null && numberTwoDouble != null)
            return mathOperations.power(numberOneDouble, numberTwoDouble)
        else
            throw BadRequestExceptions("One of the parameters is not a number.")
    }
}