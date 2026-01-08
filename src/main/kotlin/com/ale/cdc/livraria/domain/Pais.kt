package com.ale.cdc.livraria.domain

data class Pais (
    val id: Long? = null,
    val nome: String
){
    init {
        require(nome.isNotBlank()) { "Nome do Pais é obrigatório" }
    }
}