package com.ale.cdc.livraria.infrastructure.persistence.adapter

import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.infrastructure.persistence.jpa.AutorRepositoryJpa
import com.ale.cdc.livraria.infrastructure.persistence.entity.AutorEntity
import org.springframework.stereotype.Component

@Component
class AutorRepositoryAdapter(
    private val jpaRepository: AutorRepositoryJpa
) : AutorRepositoryPort {

    override fun salvar(autor: Autor) {
        val entity = AutorEntity.Companion.toEntity(autor)
        val salvo = jpaRepository.save(entity)
        salvo.toDomain()
    }

    override fun existePorEmail(email: String): Boolean {
        return jpaRepository.existsByEmail(email)
    }
}
