package com.example.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NullObjectException : RuntimeException {
    constructor(): super("Cannot handle null value received.");
    constructor(exception: String?): super(exception);
}