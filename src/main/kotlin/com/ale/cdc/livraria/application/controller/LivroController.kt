package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.LivroRequest
import com.ale.cdc.livraria.application.useCase.LivroUseCase
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/livros")
class LivroController (
    private val livroUseCase: LivroUseCase,
    private val log: Logger = LoggerFactory.getLogger(LivroController::class.java)
){

    @PostMapping
    fun criarLivro(@RequestBody @Valid request: LivroRequest): ResponseEntity<Void> {
        val livro = request.toDomain()
        log.info(livro.toString())

        livroUseCase.adicionarLivro(livro)
        return ResponseEntity.ok().build()
    }
}