package com.example.exceptionHandling.handler

import com.example.exceptionHandling.badRequest.BadRequestException
import com.example.exceptionHandling.ExceptionResponseFormat
import com.example.exceptionHandling.badRequest.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler(){
    @ExceptionHandler(Exception::class)
    fun serverSideError(exceptionInfo: Exception, webRequest: WebRequest): ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat(
            error = exceptionInfo.message,
            details = webRequest.getDescription(false),
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BadRequestException::class)
    fun clientSideError(exceptionInfo: BadRequestException, webRequest: WebRequest): ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat (
            error = exceptionInfo.message,
            details = webRequest.getDescription(false),
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundError(exceptionInfo: NotFoundException, webRequest: WebRequest) : ResponseEntity<ExceptionResponseFormat> {
        val exceptionResponse = ExceptionResponseFormat (
            error = exceptionInfo.message,
            details = webRequest.getDescription(false)
        )

        return ResponseEntity<ExceptionResponseFormat>(exceptionResponse, HttpStatus.NOT_FOUND)
    }
}