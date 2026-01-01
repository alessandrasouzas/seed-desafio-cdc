package com.ale.cdc.livraria.infrastructure.persistence.adapter

import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.domain.Livro
import com.ale.cdc.livraria.infrastructure.persistence.entity.LivroEntity
import com.ale.cdc.livraria.infrastructure.persistence.jpa.LivroRepositoryJpa
import org.springframework.stereotype.Component

@Component
class LivroRepositoryAdapter (
    private val jpaRepository: LivroRepositoryJpa
): LivroRepositoryPort {

    override fun salvar(livro: Livro) {
        val entity = LivroEntity.Companion.toEntity(livro)
        jpaRepository.save(entity)
    }

    override fun existePorTitulo(titulo: String): Boolean {
        return jpaRepository.existsByTitulo(titulo)
    }


}