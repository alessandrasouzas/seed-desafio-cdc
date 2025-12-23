package com.ale.cdc.livraria.infrastructure.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import com.ale.cdc.livraria.domain.Autor

@Entity
@Table(name = "autores")
class AutorEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    @Column(unique = true)
    val email: String,
    @Column(length = 400)
    val descricao: String,
    val instante: LocalDateTime
){
    //cria objeto para salvar no banco - static
    companion object {
        fun toEntity(autor: Autor): AutorEntity =
            AutorEntity(
                nome = autor.nome,
                email = autor.email,
                descricao = autor.descricao,
                instante = autor.instante
            )
    }
    // transforma um objeto ja existente do banco
    fun toDomain(): Autor =
        Autor(
            nome = nome,
            email = email,
            descricao = descricao,
            instante = instante
        )
}