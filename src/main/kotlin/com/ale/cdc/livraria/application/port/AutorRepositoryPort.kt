package com.ale.cdc.livraria.application.port

import com.ale.cdc.livraria.application.controller.request.AutorRequest
import com.ale.cdc.livraria.domain.Autor

interface AutorRepositoryPort {
    fun salvar(autor: Autor)
    fun existePorEmail(email: String): Boolean
}