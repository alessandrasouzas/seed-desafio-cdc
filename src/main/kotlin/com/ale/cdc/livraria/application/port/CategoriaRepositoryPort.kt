package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.infrastructure.persistence.entity.CategoriaEntity

interface CategoriaRepositoryPort {
    fun salvar(categoria: Categoria)
    fun existsByNome(nome: String): Boolean
    fun buscaPorId(id: Long): CategoriaEntity
}