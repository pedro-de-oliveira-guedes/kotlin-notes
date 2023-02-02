package com.example.custom_exceptions

import java.sql.Timestamp
import java.util.Date

class ExceptionResponseFormat (
    val timestamp: Date = Date(),
    val message: String? = "An error occurred.",
    val details: String? = "Error!"
)