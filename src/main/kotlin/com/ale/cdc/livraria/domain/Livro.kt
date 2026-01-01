package com.ale.cdc.livraria.domain

import java.time.LocalDate

data class Livro (
    val id: Long? = null,
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: Double,
    val numero_Paginas: Int,
    val isbn: String,
    val data_publicacao: LocalDate,
    val autor: Autor?,
    val categoria: Categoria?
){
    init {
        require(titulo.isNotBlank()) { "Titulo é obrigatório" }
        require(resumo.isNotBlank() && resumo.length <= 500) { "Resumo é obrigatório" }
        require(preco>=20) { "Preço é obrigatório" }
        require(numero_Paginas>=100) { "Numero de paginas é obrigatório" }
        require(isbn.isNotBlank()) { "ISBN é obrigatório" }
        require(data_publicacao.isAfter(LocalDate.now().plusDays(1))) {"Data de publicação deve ser superior a 1 dia no futuro" }
    }
}