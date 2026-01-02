package com.ale.cdc.livraria.domain

data class Categoria(
    val id: Long? = null,
    var nome: String
) {
    init {
        require(nome.isNotBlank()) { "Nome da categoria é obrigatório" }
    }
}