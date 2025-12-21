package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.controller.request.AutorRequest
import com.ale.cdc.livraria.domain.Autor
import org.springframework.stereotype.Service

@Service
class AutorUseCase {

    fun adicionarAutor(request: AutorRequest): Autor {
        val autor = Autor(
            nome = request.nome,
            email = request.email,
            descricao = request.descricao
        )

        println("Autor criado no domínio: $autor")
        return autor
    }
}
