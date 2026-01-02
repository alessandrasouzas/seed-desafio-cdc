package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.LivroRequest
import com.ale.cdc.livraria.application.controller.response.LivroResponse
import com.ale.cdc.livraria.application.useCase.LivroUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/livros")
class LivroController (
    private val livroUseCase: LivroUseCase
){

    @PostMapping
    fun criarLivro(@RequestBody @Valid request: LivroRequest): ResponseEntity<Void> {
        val cmd = request.toCommand()
        livroUseCase.adicionarLivro(cmd)

        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun buscarLivros(): List<LivroResponse> {
        return livroUseCase.buscarLivros()
    }
}