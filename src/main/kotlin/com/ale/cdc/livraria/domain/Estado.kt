package com.ale.cdc.livraria.domain

data class Estado (
    val id: Long?,
    val nome: String,
    val pais: Pais
) {
    init {
        require(nome.isNotBlank()) { "Nome do estado é obrigatório" }
    }
}