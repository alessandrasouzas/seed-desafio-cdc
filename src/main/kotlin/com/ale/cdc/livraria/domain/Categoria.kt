package com.ale.cdc.livraria.domain

data class Categoria (
    var nome: String
){
    init {
        require(nome.isNotBlank())
    }
}