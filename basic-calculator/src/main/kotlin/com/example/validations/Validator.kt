package com.example.validations

class Validator {
    fun isNumber(strNumber: String): Double? {
        val treatedStrNumber = strNumber.replace(',', '.')

        return treatedStrNumber.toDoubleOrNull()
    }
}