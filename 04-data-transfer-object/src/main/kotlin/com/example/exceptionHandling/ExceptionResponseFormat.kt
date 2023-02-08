package com.example.exceptionHandling

import java.util.Date

class ExceptionResponseFormat (
    val timestamp: Date = Date(),
    val error: String?,
    val details: String?,
)