package com.solr.solrproject.exception.not_found

import com.solr.solrproject.exception.CustomException
import org.springframework.http.HttpStatus

class ProductNotFoundException(id : String?) : CustomException("Product with id: $id does not exist"){

    private val ERROR_CODE = "EP001"

    init{
        this.errorCode = ERROR_CODE
        this.httpStatus = HttpStatus.NOT_FOUND
    }
}