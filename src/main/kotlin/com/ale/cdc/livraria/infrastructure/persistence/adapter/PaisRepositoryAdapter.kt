package com.ale.cdc.livraria.infrastructure.persistence.adapter

import com.ale.cdc.livraria.application.port.PaisRepositoryPort
import com.ale.cdc.livraria.domain.Pais
import com.ale.cdc.livraria.infrastructure.persistence.entity.PaisEntity
import com.ale.cdc.livraria.infrastructure.persistence.jpa.PaisRepositoryJpa
import org.springframework.stereotype.Component

@Component
class PaisRepositoryAdapter(
    private val jpaRepository: PaisRepositoryJpa
): PaisRepositoryPort {

    override fun existePorNome(nome: String): Boolean {
        return jpaRepository.existsByNome(nome)
    }

    override fun salvar(pais: Pais) {
        val entity = PaisEntity.toEntity(pais)
        jpaRepository.save(entity)
    }
}