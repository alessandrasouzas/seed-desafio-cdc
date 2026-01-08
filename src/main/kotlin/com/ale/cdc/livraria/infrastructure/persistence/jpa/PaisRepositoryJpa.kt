package com.ale.cdc.livraria.infrastructure.persistence.jpa

import com.ale.cdc.livraria.infrastructure.persistence.entity.EstadoEntity
import com.ale.cdc.livraria.infrastructure.persistence.entity.PaisEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaisRepositoryJpa  : JpaRepository<PaisEntity, Long> {
    fun existsByNome(nome: String): Boolean
    fun save(entity: EstadoEntity)
}


