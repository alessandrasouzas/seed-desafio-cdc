package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.PaisRepositoryPort
import com.ale.cdc.livraria.application.useCase.command.CriarEstadoCommand
import com.ale.cdc.livraria.domain.Estado
import com.ale.cdc.livraria.domain.Pais
import com.ale.cdc.livraria.domain.exception.PaisNomeException
import com.ale.cdc.livraria.domain.exception.PaisNotFoundException
import com.ale.cdc.livraria.domain.shared.NomeNormalizer
import org.springframework.stereotype.Service

@Service
class PaisUseCase (
    private val paisRepositoryPort: PaisRepositoryPort
){

    fun adicionarPais(pais: Pais) {

        if(paisRepositoryPort.existePorNome(pais.nome))
            throw PaisNomeException(pais.nome)

        val country = Pais(pais.id, NomeNormalizer.normalizar(pais.nome))
        paisRepositoryPort.salvar(country)
    }

    fun adicionarEstado(cmd: CriarEstadoCommand) {

        if(paisRepositoryPort.existePorNome(cmd.nome))
            throw PaisNomeException(cmd.nome)

        if(!paisRepositoryPort.existePorId(cmd.paisId))
            throw PaisNotFoundException(cmd.paisId)

        val pais = paisRepositoryPort.buscarPorId(cmd.paisId)

        val estado = Estado(
            id = null,
            nome = NomeNormalizer.normalizar(cmd.nome),
            pais = pais
        )
        paisRepositoryPort.salvar(estado)
    }

}