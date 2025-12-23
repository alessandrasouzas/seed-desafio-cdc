package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.controller.request.AutorRequest
import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.exception.EmailException
import org.springframework.stereotype.Service

@Service
class AutorUseCase (
    private val autorRepository: AutorRepositoryPort
){
    fun adicionarAutor(autor: Autor) {
        if (autorRepository.existePorEmail(autor.email)) {
            throw EmailException(autor.email)
        }
        else autorRepository.salvar(autor)
    }
}
