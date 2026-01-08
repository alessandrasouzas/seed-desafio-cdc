package com.ale.cdc.livraria.infrastructure.persistence.entity

import com.ale.cdc.livraria.domain.Estado
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "estado")
class EstadoEntity(

    @Id
    @GeneratedValue
    val id: Long? = null,
    val nome: String,

    @ManyToOne(optional = false)
    @JoinColumn(name = "pais_id")
    val pais: PaisEntity
) {
    companion object {
        fun toEntity(estado: Estado, paisEntity: PaisEntity): EstadoEntity =
            EstadoEntity(
                nome = estado.nome,
                pais = paisEntity
            )
    }

    fun toDomain(): Estado =
        Estado(
            id = id,
            nome = nome,
            pais = pais.toDomain()
        )
}
