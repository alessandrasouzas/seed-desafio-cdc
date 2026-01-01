package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.domain.Livro
import com.ale.cdc.livraria.domain.exception.TituloException
import org.springframework.stereotype.Service

@Service
class LivroUseCase (
    private val livroRepositoryPort: LivroRepositoryPort
) {

    fun adicionarLivro(request: Livro) {
        if(livroRepositoryPort.existePorTitulo(request.titulo))
            throw TituloException(request.titulo)
        else livroRepositoryPort.salvar(request)
    }
}