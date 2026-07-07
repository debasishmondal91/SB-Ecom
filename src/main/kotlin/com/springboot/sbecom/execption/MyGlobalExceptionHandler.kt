package com.springboot.sbecom.execption

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun myMethodArgumentException(e: MethodArgumentNotValidException) : ResponseEntity<Map<String, String>> {
        val responseBody = HashMap<String, String>()
        e.bindingResult.allErrors.forEach {error ->
            val fieldName = (error as FieldError).field
            val message = error.defaultMessage
            if (message != null) {
                responseBody[fieldName] = message
            }
        }

        return ResponseEntity(responseBody, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ApiException::class)
    fun myApiException(e: ApiException) : ResponseEntity<String> {
        val message = e.message
        return ResponseEntity(message, HttpStatus.BAD_REQUEST)
    }
}