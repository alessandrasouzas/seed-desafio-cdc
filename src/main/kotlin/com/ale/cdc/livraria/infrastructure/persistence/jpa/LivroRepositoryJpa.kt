package com.ale.cdc.livraria.infrastructure.persistence.jpa

import com.ale.cdc.livraria.infrastructure.persistence.entity.LivroEntity
import com.ale.cdc.livraria.infrastructure.persistence.jpa.projection.LivroTituloProjection
import org.springframework.data.jpa.repository.JpaRepository

interface LivroRepositoryJpa  : JpaRepository<LivroEntity, Long>{
    fun save(entity: LivroEntity)
    fun existsByTitulo(string: String): Boolean
    fun findAllByOrderByIdAsc(): List<LivroTituloProjection>
}