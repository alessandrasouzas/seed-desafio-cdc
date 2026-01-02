package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.infrastructure.persistence.entity.AutorEntity

interface AutorRepositoryPort {
    fun salvar(autor: Autor)
    fun existePorEmail(email: String): Boolean
    fun buscaPorId(id: Long): AutorEntity
    fun existePorId(id: Long): Boolean
}