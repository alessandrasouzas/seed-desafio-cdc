package com.ale.cdc.livraria.infrastructure.exception

import com.ale.cdc.livraria.domain.exception.CategoriaException
import com.ale.cdc.livraria.domain.exception.EmailException
import com.ale.cdc.livraria.domain.exception.TituloException
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
            .status(HttpStatus.BAD_REQUEST)
            .body(MessageError(
                code = 400.toString(),
                message = "Categoria já cadastrada!"
            ))

    @ExceptionHandler(TituloException::class)
    fun handle(ex: TituloException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(MessageError(
                code = 400.toString(),
                message = "Titulo já foi adicionado!"
            ))

}