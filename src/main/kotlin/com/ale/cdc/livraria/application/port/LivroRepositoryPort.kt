package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Livro

interface LivroRepositoryPort {
    fun existePorTitulo(titulo: String): Boolean
    fun salvar(livro: Livro, autorId: Long, categoriaId: Long)
    fun buscarLivros(): List<Livro>
}