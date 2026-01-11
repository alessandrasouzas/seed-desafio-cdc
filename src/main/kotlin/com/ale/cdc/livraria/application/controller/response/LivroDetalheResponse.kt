package com.ale.cdc.livraria.application.controller.response

import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.livro.Formato
import com.ale.cdc.livraria.domain.livro.Livro
import java.time.LocalDate

data class LivroDetalheResponse (
    val id: Long,
    val titulo: String,
    val subtitulo: String,
    val resumo: String,
    val sumario: String,
    val formatos: List<Formato>?,
    val numeroPaginas: Int,
    val isbn: String,
    val dataLancamento: LocalDate,
    val capaLivro: String?,
    val autor: Autor,
    val categoria: Categoria
){
    companion object{
        fun toResponse(livro: Livro): LivroDetalheResponse {
            return LivroDetalheResponse(
                id = livro.id!!,
                titulo = livro.titulo,
                resumo = livro.resumo,
                sumario = livro.sumario,
                subtitulo = livro.subtitulo!!,
                formatos = livro.formatos,
                numeroPaginas = livro.numeroPaginas,
                isbn = livro.isbn.codigoLivro,
                dataLancamento = livro.dataLancamento,
                capaLivro = livro.capaLivro?.url,
                autor = livro.autor!!,
                categoria = livro.categoria!!
            )
        }
    }
}