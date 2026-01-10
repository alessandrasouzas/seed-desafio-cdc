package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.LivroRequest
import com.ale.cdc.livraria.application.controller.response.LivroDetalheResponse
import com.ale.cdc.livraria.application.controller.response.LivroResponse
import com.ale.cdc.livraria.application.controller.response.LivroTituloResponse
import com.ale.cdc.livraria.application.useCase.LivroUseCase
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/livros")
class LivroController{

    @Autowired
    lateinit var livroUseCase: LivroUseCase

    @PostMapping
    fun adicionarLivro(@RequestBody @Valid request: LivroRequest): ResponseEntity<Void> {
        val cmd = request.toCommand()
        livroUseCase.adicionarLivro(cmd)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun buscarLivros(): List<LivroResponse> {
        return livroUseCase.buscarLivros()
    }

    @GetMapping("/titulos")
    fun buscarTitulos(): List<LivroTituloResponse> {
        return livroUseCase.buscarTitulos()
    }

    @GetMapping("/detalhe/{id}")
    fun buscarLivroDetalhe(@PathVariable id: Long): LivroDetalheResponse{
        return livroUseCase.buscarDetalheLivro(id)
    }
}