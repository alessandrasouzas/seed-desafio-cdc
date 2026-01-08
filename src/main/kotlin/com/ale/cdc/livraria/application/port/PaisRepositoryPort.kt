package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.domain.Estado
import com.ale.cdc.livraria.domain.Pais

interface PaisRepositoryPort {
    fun existePorNome(nome: String): Boolean
    fun salvar(pais: Pais)
    fun salvar(estado: Estado)
    fun existePorId(id: Long): Boolean
    fun buscarPorId(id: Long): Pais
}