package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Categoria

interface CategoriaRepositoryPort {
    fun salvar(categoria: Categoria)
    fun existsByNome(nome: String): Boolean
}