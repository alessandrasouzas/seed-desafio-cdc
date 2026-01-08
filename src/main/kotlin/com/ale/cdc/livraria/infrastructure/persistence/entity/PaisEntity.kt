package com.ale.cdc.livraria.infrastructure.persistence.entity

import com.ale.cdc.livraria.domain.Pais
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "paises")
class PaisEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val nome: String
){
    companion object {
        fun toEntity(pais: Pais): PaisEntity =
            PaisEntity(nome = pais.nome)
    }

    fun toDomain(): Pais = Pais(
        id = id,
        nome = nome
    )

}