package com.example.custom_exceptions

import java.sql.Timestamp
import java.util.Date

class ExceptionResponseFormat (
    timestamp: Date = Date(),
    message: String? = "An error occurred.",
    details: String? = "Error!"
)