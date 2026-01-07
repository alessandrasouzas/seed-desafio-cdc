package com.ale.cdc.livraria.domain.livro

data class Isbn(
    val codigoLivro: String
){
    init {
        require(codigoLivro.isNotBlank()) { "ISBN é obrigatório" }
    }
}
