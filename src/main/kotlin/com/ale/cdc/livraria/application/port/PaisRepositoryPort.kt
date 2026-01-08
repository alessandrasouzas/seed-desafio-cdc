package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.application.controller.request.PaisRequest
import com.ale.cdc.livraria.domain.Pais

interface PaisRepositoryPort {
    fun existePorNome(nome: String): Boolean
    fun salvar(pais: Pais)
}