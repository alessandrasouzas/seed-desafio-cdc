package com.ale.cdc.livraria.domain.livro

import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.Categoria
import java.time.LocalDate

data class Livro (
    val id: Long? = null,
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val formatos: List<Formato>,
    val numeroPaginas: Int,
    val isbn: Isbn,
    val dataLancamento: LocalDate,
    val capaLivro: UrlCapa,
    val autor: Autor?,
    val categoria: Categoria?
){
    init {
        require(titulo.isNotBlank()) { "Titulo é obrigatório" }
        require(resumo.isNotBlank() && resumo.length <= 500) { "Resumo é obrigatório" }
        require(numeroPaginas>=100) { "Numero de paginas é obrigatório" }
        require(dataLancamento.isAfter(LocalDate.now().plusDays(1))) {"Data de publicação deve ser superior a 1 dia no futuro" }
    }
}