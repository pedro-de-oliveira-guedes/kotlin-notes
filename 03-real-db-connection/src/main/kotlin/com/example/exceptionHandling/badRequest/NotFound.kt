package com.example.exceptionHandling.badRequest

import java.lang.Exception

class NotFoundException(
    private val exceptionMessage: String
) : Exception(exceptionMessage)