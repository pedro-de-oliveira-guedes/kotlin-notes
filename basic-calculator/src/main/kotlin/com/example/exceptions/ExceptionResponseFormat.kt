package com.example.exceptions

import java.util.Date

class ExceptionResponseFormat(
    val timestamp: Date = Date(),
    val message: String? = "Error!",
    val details: String? = "An error occurred!",)