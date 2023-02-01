package com.example.mathOperations

import kotlin.math.pow

class mathOperations {
    fun sum(numberOne: Double, numberTwo: Double,) = numberOne + numberTwo

    fun sub(numberOne: Double, numberTwo: Double,) = numberOne - numberTwo

    fun mul(numberOne: Double, numberTwo: Double,) = numberOne * numberTwo

    fun div(numberOne: Double, numberTwo: Double,) = numberOne / numberTwo

    fun avg(numberOne: Double, numberTwo: Double,) = (numberOne + numberTwo) / 2

    fun sqrt(numberOne: Double,) = kotlin.math.sqrt(numberOne)

    fun power(numberOne: Double, numberTwo: Double,) = numberOne.pow(numberTwo)
}