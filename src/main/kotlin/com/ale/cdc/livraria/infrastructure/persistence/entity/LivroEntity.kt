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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    val autor: AutorEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    val categoria: CategoriaEntity
){

    companion object {
        fun toEntity(
            livro: Livro,
            autorEntity: AutorEntity,
            categoriaEntity: CategoriaEntity
        ): LivroEntity =
            LivroEntity(
                titulo = livro.titulo,
                resumo = livro.resumo,
                sumario = livro.sumario,
                preco = livro.preco,
                numero_Paginas = livro.numero_Paginas,
                isbn = livro.isbn,
                data_publicacao = livro.data_publicacao,
                autor = autorEntity,
                categoria = categoriaEntity
            )
    }

    fun toDomain(): Livro =
        Livro(
            id = id,
            titulo = titulo,
            resumo = resumo,
            sumario = sumario,
            preco = preco,
            numero_Paginas = numero_Paginas,
            isbn = isbn,
            data_publicacao = data_publicacao,
            autor = autor.toDomain(),
            categoria = categoria.toDomain()
        )

}