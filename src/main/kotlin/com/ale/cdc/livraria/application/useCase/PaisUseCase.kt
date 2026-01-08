package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.PaisRepositoryPort
import com.ale.cdc.livraria.domain.Pais
import org.springframework.stereotype.Service

@Service
class PaisUseCase (
    private val paisRepositoryPort: PaisRepositoryPort
){

    fun adicionarPais(pais: Pais) {
        if(paisRepositoryPort.existePorNome(pais.nome))
            throw Exception()
        paisRepositoryPort.salvar(pais)
    }

}