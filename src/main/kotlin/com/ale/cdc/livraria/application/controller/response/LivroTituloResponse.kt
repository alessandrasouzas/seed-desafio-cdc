package com.ale.cdc.livraria.application.controller.response

import com.ale.cdc.livraria.domain.Livro

data class LivroTituloResponse(
    val id: Long,
    val titulo: String
){
    companion object {
        fun toResponse(livro: Livro): LivroTituloResponse {
            return LivroTituloResponse(
                id = livro.id!!,
                titulo = livro.titulo
            )
        }
    }
}