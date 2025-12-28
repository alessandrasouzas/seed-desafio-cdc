package com.ale.cdc.livraria.infrastructure.exception

import com.ale.cdc.livraria.domain.exception.CategoriaException
import com.ale.cdc.livraria.domain.exception.EmailException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(EmailException::class)
    fun handle(ex: EmailException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(MessageError(
                code = 422.toString(),
                message = "Email já cadastrado!"
            ))

    @ExceptionHandler(CategoriaException::class)
    fun handle(ex: CategoriaException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(MessageError(
                code = 422.toString(),
                message = "Categoria já cadastrada!"
            ))
}