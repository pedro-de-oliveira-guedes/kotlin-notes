package com.example.exceptionHandling

import java.lang.Exception

class BadRequestException (
    private val exceptionMessage: String
): Exception(exceptionMessage)