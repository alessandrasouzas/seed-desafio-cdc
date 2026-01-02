package com.ale.cdc.livraria.application.controller.response

import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.Livro
import java.time.LocalDate

data class LivroResponse(
    val id: Long,
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: Double,
    val numero_Paginas: Int,
    val isbn: String,
    val data_lancamento: LocalDate,
    val autor: Autor?,
    val categoria: Categoria?
) {

    companion object {
        fun toResponse(livro: Livro): LivroResponse {
            return LivroResponse(
                id = livro.id!!,
                titulo = livro.titulo,
                resumo = livro.resumo,
                sumario = livro.sumario,
                preco = livro.preco,
                numero_Paginas = livro.numero_Paginas,
                isbn = livro.isbn,
                data_lancamento = livro.data_publicacao,
                autor = livro.autor,
                categoria = livro.categoria
            )
        }
    }
}