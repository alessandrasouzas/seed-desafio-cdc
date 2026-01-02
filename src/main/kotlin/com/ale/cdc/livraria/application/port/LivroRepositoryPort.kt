package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Livro
import com.ale.cdc.livraria.infrastructure.persistence.jpa.projection.LivroTituloProjection

interface LivroRepositoryPort {
    fun existePorTitulo(titulo: String): Boolean
    fun salvar(livro: Livro, autorId: Long, categoriaId: Long)
    fun buscarLivros(): List<Livro>
    fun buscarTitulos(): List<LivroTituloProjection>
}