package com.solr.solrproject.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class ExceptionResponseHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(CustomException::class)
    fun handleCustomExceptions(ex: CustomException): ResponseEntity<CustomExceptionResponse> {
        return ResponseEntity.status(ex.httpStatus).body(CustomExceptionResponse(ex))
    }
}