package com.ale.cdc.livraria.infrastructure.persistence.adapter

import com.ale.cdc.livraria.application.port.PaisRepositoryPort
import com.ale.cdc.livraria.domain.Estado
import com.ale.cdc.livraria.domain.Pais
import com.ale.cdc.livraria.infrastructure.persistence.entity.EstadoEntity
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

    override fun existePorId(id: Long): Boolean {
        return jpaRepository.existsById(id)
    }

    override fun buscarPorId(id: Long): Pais {
        return jpaRepository.getReferenceById(id).toDomain()
    }

    override fun salvar(estado: Estado) {
        val paisEntity = jpaRepository.getReferenceById(estado.pais.id!!)
        val estadoEntity = EstadoEntity.toEntity(estado, paisEntity)
        jpaRepository.save(estadoEntity)
    }

}