package com.ale.cdc.livraria.infrastructure.persistence.jpa

import com.ale.cdc.livraria.infrastructure.persistence.entity.AutorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AutorRepositoryJpa : JpaRepository<AutorEntity, Long> {
    fun existsByEmail(email: String): Boolean
}