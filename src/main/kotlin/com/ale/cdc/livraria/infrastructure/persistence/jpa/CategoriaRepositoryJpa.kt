package com.ale.cdc.livraria.infrastructure.persistence.jpa

import com.ale.cdc.livraria.infrastructure.persistence.entity.CategoriaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaRepositoryJpa : JpaRepository<CategoriaEntity, Long> {
    fun existsByNome(nome: String): Boolean
}