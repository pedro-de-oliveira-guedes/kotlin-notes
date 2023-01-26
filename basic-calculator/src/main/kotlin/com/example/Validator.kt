package com.example

class Validator {
    fun isNumber(strNumber: String): Double? {
        val treatedStrNumber = strNumber.replace(',', '.')

        return treatedStrNumber.toDoubleOrNull()
    }
}