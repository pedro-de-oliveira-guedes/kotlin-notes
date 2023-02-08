package com.example.exceptionHandling.badRequest

import java.lang.Exception

class BadRequestException (
    private val exceptionMessage: String
): Exception(exceptionMessage)