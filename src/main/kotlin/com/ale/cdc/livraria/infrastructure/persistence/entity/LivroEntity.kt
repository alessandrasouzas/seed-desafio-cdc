package com.ale.cdc.livraria.infrastructure.persistence.entity

import com.ale.cdc.livraria.domain.Livro
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "livros")
data class LivroEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: Double,
    val numero_Paginas: Int,
    @Column(unique = true)
    val isbn: String,
    val data_publicacao: LocalDate,
    val autor: String,
    val categoria: String
){

    companion object {
        fun toEntity(
            livro: Livro
        ): LivroEntity =
            LivroEntity(
                id = livro.id,
                titulo = livro.titulo,
                resumo = livro.resumo,
                sumario = livro.sumario,
                preco = livro.preco,
                numero_Paginas = livro.numero_Paginas,
                isbn = livro.isbn,
                data_publicacao = livro.data_publicacao,
                autor = livro.autor,
                categoria = livro.categoria
            )
    }

    fun toDomain(): Livro =
        Livro(
            id = this.id,
            titulo = this.titulo,
            resumo = this.resumo,
            sumario = this.sumario,
            preco = this.preco,
            numero_Paginas = this.numero_Paginas,
            isbn = this.isbn,
            data_publicacao = this.data_publicacao,
            autor = this.autor,
            categoria = this.categoria
        )
}