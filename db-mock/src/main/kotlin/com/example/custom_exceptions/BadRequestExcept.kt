package com.example.custom_exceptions

class BadRequestExcept (message: String = "There is an unexpected error in the request.",): RuntimeException(message)