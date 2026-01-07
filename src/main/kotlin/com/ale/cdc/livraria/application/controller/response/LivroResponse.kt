package com.ale.cdc.livraria.application.controller.response

import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.livro.Livro
import com.ale.cdc.livraria.domain.livro.Formato
import java.time.LocalDate

data class LivroResponse(
    val id: Long,
    val titulo: String,
    val resumo: String?,
    val sumario: String?,
    val formato: List<Formato>,
    val numeroPaginas: Int?,
    val isbn: String?,
    val dataLancamento: LocalDate?,
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
                formato = livro.formatos,
                numeroPaginas = livro.numeroPaginas,
                isbn = livro.isbn.codigoLivro,
                dataLancamento = livro.dataLancamento,
                autor = livro.autor,
                categoria = livro.categoria
            )
        }
    }

}