package com.ale.cdc.livraria.application.controller.request

import com.ale.cdc.livraria.domain.Categoria
import jakarta.validation.constraints.NotBlank

data class  CategoriaRequest(
    @field:NotBlank
    val nome: String
) {
    fun toDomain(): Categoria =
        Categoria(
            nome = nome
        )
}
