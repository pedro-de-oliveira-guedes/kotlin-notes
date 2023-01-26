package com.example.exceptions

class BadRequestExceptions(exception: String? = "There is an unspecified error in the request. Check it and try again!",):
    RuntimeException(exception)