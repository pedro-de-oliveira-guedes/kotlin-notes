package com.example.exceptions

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

class BadRequestExceptions(exception: String? = "There is an unspecified error in the request. Check it and try again!"): RuntimeException() {

}