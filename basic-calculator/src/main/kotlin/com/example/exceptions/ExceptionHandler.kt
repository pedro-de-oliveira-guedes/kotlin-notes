package com.example.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.util.*

@ControllerAdvice
@RestController
class ExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleServerSideExceptions(exception: Exception, request: WebRequest): ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat(
            Date(),
            exception.message,
            request.getDescription(false)
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(BadRequestExceptions::class)
    fun handleClientSideExceptions(exception: BadRequestExceptions, request: WebRequest): ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat(
            Date(),
            exception.message,
            request.getDescription(false)
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}