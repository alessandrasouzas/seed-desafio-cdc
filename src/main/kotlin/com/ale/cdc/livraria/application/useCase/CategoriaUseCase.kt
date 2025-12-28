package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.exception.CategoriaException
import org.springframework.stereotype.Service

@Service
class CategoriaUseCase (
    private val categoriaRepository: CategoriaRepositoryPort
){
    fun adicionarCategoria(categoria: Categoria) {
        if (categoriaRepository.existsByNome(categoria.nome))
            throw CategoriaException(categoria.nome)
        else categoriaRepository.salvar(categoria)
    }
}