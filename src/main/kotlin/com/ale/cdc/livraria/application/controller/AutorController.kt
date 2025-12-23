package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.AutorRequest
import com.ale.cdc.livraria.application.useCase.AutorUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/autores")
class AutorController(
    private val autorUseCase: AutorUseCase
) {

    @PostMapping
    fun criarAutor(@RequestBody @Valid request: AutorRequest): ResponseEntity<Void> {
        val autor = request.toDomain()

        autorUseCase.adicionarAutor(autor)
        return ResponseEntity.ok().build()
    }
}
