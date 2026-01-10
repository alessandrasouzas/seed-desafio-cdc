package com.ale.cdc.livraria.infrastructure.persistence.entity.livro

import com.ale.cdc.livraria.domain.livro.Livro
import com.ale.cdc.livraria.domain.livro.Formato
import com.ale.cdc.livraria.domain.livro.Isbn
import com.ale.cdc.livraria.domain.livro.UrlCapa
import com.ale.cdc.livraria.infrastructure.persistence.entity.AutorEntity
import com.ale.cdc.livraria.infrastructure.persistence.entity.CategoriaEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
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
    val capaUrl: String,
    val subtitulo: String,
    val numeroPaginas: Int,
    @Column(unique = true)
    val isbn: String,
    val data_publicacao: LocalDate,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    val autor: AutorEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    val categoria: CategoriaEntity,

    @ElementCollection
    @CollectionTable(
        name = "livro_formato",
        joinColumns = [JoinColumn(name = "livro_id")]
    )
    val formatos: List<FormatoEmbeddable>
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
                formatos = livro.formatos!!.map {
                    FormatoEmbeddable(
                        tipo = it.tipo,
                        preco = it.preco
                    )
                },
                capaUrl = livro.capaLivro.toString(),
                numeroPaginas = livro.numeroPaginas,
                isbn = livro.isbn.codigoLivro,
                data_publicacao = livro.dataLancamento,
                subtitulo = livro.subtitulo!!,
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
            capaLivro = UrlCapa(capaUrl),
            formatos =  formatos.map {
                Formato(
                    tipo = it.tipo,
                    preco = it.preco
                )
            },
            numeroPaginas = numeroPaginas,
            isbn = Isbn(isbn),
            dataLancamento = data_publicacao,
            subtitulo = subtitulo,
            autor = autor.toDomain(),
            categoria = categoria.toDomain()
        )

}