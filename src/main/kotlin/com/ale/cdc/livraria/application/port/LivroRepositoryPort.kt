package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Livro

interface LivroRepositoryPort {
    fun salvar(livro: Livro)
    fun existePorTitulo(titulo: String): Boolean
}