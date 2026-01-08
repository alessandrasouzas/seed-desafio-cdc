package com.ale.cdc.livraria.application.controller.request

import com.ale.cdc.livraria.domain.Pais
import jakarta.validation.constraints.NotBlank

data class PaisRequest (

    @field:NotBlank
    val nome: String
){
    init {
        require(nome.isNotBlank()) {"nome do páis é obrigatorio"}
    }

    fun toDomain(): Pais =
        Pais(
            nome = nome
        )
}