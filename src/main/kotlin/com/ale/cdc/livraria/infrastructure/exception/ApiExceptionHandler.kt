package com.ale.cdc.livraria.infrastructure.exception

import com.ale.cdc.livraria.domain.exception.AutorNotFoundException
import com.ale.cdc.livraria.domain.exception.CategoriaException
import com.ale.cdc.livraria.domain.exception.CategoriaNotFoundException
import com.ale.cdc.livraria.domain.exception.DocumentoInvalidoException
import com.ale.cdc.livraria.domain.exception.EmailException
import com.ale.cdc.livraria.domain.exception.LivroNotFoundException
import com.ale.cdc.livraria.domain.exception.PaisNomeException
import com.ale.cdc.livraria.domain.exception.PaisNotFoundException
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
            .status(HttpStatus.BAD_REQUEST)
            .body(MessageError(
                code = 400.toString(),
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
                message = "Titulo já cadastrado!"
            ))

    @ExceptionHandler(AutorNotFoundException::class)
    fun handle(ex: AutorNotFoundException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(MessageError(
                code = 404.toString(),
                message = "Autor não encontrado."
            ))

    @ExceptionHandler(CategoriaNotFoundException::class)
    fun handle(ex: CategoriaNotFoundException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(MessageError(
                code = 404.toString(),
                message = "Categoria não encontrada."
            ))

    @ExceptionHandler(LivroNotFoundException::class)
    fun handle(ex: LivroNotFoundException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(MessageError(
                code = 404.toString(),
                message = "Livro não encontrado."
            ))

    @ExceptionHandler(PaisNomeException::class)
    fun handle(ex: PaisNomeException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(MessageError(
                code = 400.toString(),
                message = "Pais já cadastrado!"
            ))

    @ExceptionHandler(PaisNotFoundException::class)
    fun handle(ex: PaisNotFoundException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(MessageError(
                code = 404.toString(),
                message = "Pais não encontrado."
            ))

    @ExceptionHandler(DocumentoInvalidoException::class)
    fun handle(ex: DocumentoInvalidoException): ResponseEntity<MessageError> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(MessageError(
                code = 400.toString(),
                message = "Documento invalido."
            ))
}