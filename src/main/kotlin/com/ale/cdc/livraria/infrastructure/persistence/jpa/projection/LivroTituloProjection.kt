package com.ale.cdc.livraria.infrastructure.persistence.jpa.projection

interface LivroTituloProjection {
    val id: Long
    val titulo: String
}