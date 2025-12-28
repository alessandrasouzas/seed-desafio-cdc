package com.ale.cdc.livraria.infrastructure.persistence.entity

import com.ale.cdc.livraria.domain.Categoria
import jakarta.persistence.*

@Entity
@Table(name = "categoria")
class CategoriaEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val nome: String
){

    //cria objeto para salvar no banco - static
    companion object {
        fun toEntity(categoria: Categoria): CategoriaEntity =
            CategoriaEntity(
                nome = categoria.nome
            )
    }

    // transforma um objeto ja existente do banco
    fun toDomain(): Categoria =
        Categoria(
            nome = nome
        )
}