package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.exception.CategoriaException
import com.ale.cdc.livraria.domain.shared.NomeNormalizer
import org.springframework.stereotype.Service

@Service
class CategoriaUseCase (
    private val categoriaRepository: CategoriaRepositoryPort
){
    fun adicionarCategoria(categoria: Categoria) {

        //garante que a categoria deve sempre ser criada com o nome normalizado (ex: Suspense) para evitar duplicidade semântica
        categoria.nome = NomeNormalizer.normalizar(categoria.nome)

        if (categoriaRepository.existsByNome(categoria.nome))
            throw CategoriaException(categoria.nome)
        categoriaRepository.salvar(categoria)
    }
}