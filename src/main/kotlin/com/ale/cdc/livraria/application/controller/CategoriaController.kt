package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.CategoriaRequest
import com.ale.cdc.livraria.application.useCase.CategoriaUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/categorias")
class CategoriaController (
    private val categoriaUseCase: CategoriaUseCase
) {

    @PostMapping
    fun criarCategoria(@RequestBody @Valid request: CategoriaRequest): ResponseEntity<Void> {
        val categoria = request.toDomain()

        categoriaUseCase.adicionarCategoria(categoria)
        return ResponseEntity.ok().build()
    }
}