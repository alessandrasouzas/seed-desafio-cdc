package com.ale.cdc.livraria.application.controller.request

import com.ale.cdc.livraria.domain.Autor
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AutorRequest(
    @field:NotBlank
    val nome: String,
    @field:Email
    @field:NotBlank
    val email: String,
    @field:NotBlank
    @field:Size(max = 400)
    val descricao: String
) {
    fun toDomain(): Autor =
        Autor(
            nome = nome,
            email = email,
            descricao = descricao
        )
}