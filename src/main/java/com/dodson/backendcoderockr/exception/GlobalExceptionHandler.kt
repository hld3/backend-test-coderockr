package com.dodson.backendcoderockr.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun requestValidationError(exception: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        logger.info("There were request validation errors.")
        val errors =
            exception.bindingResult.fieldErrors.associate {
                it.field to it.defaultMessage
            }
        return ResponseEntity.badRequest().body(errors)
    }
}
