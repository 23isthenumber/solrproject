package com.solr.solrproject.exception

import org.springframework.http.HttpStatus

class CustomExceptionResponse (customException: CustomException){

    private var errorCode: String? = customException.errorCode
    private var message: String? = customException.message
    private var httpStatus: HttpStatus? = customException.httpStatus
    private var objects: Map<Any, Any>? = customException.objects
}