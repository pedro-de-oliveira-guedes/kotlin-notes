package com.example.custom_exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class ExceptionHandler : ResponseEntityExceptionHandler(){
    @ExceptionHandler(Exception::class)
    fun serverSideErrors(exception: java.lang.Exception, request: WebRequest): ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat(
            message = exception.message,
            details = request.getDescription(false),
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BadRequestExcept::class)
    fun clientSideErrors(exception: BadRequestExcept, request: WebRequest): ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat(
            message = exception.message,
            details = request.getDescription(false),
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}