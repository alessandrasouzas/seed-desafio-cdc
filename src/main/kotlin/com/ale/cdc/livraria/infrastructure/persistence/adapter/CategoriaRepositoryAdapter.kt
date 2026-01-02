package com.ale.cdc.livraria.infrastructure.persistence.adapter

import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.infrastructure.persistence.entity.CategoriaEntity
import com.ale.cdc.livraria.infrastructure.persistence.jpa.CategoriaRepositoryJpa
import org.springframework.stereotype.Component

@Component
class CategoriaRepositoryAdapter (
    private val jpaRepository : CategoriaRepositoryJpa
) : CategoriaRepositoryPort {

    override fun salvar(categoria: Categoria) {
        val entity = CategoriaEntity.Companion.toEntity(categoria)
        jpaRepository.save(entity)
    }

    override fun existsByNome(nome: String): Boolean {
        return jpaRepository.existsByNome(nome)
    }

    override fun buscaPorId(id: Long): CategoriaEntity {
        return jpaRepository.getReferenceById(id)
    }

    override fun existePorId(id: Long): Boolean {
        return jpaRepository.existsById(id)
    }
}