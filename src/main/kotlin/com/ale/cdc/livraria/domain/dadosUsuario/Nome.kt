package com.ale.cdc.livraria.domain.dadosUsuario

data class Nome (
    val nome: String,
    val sobrenome: String,
){
    init {
        require(nome.isNotBlank()) {"nome é obrigatorio"}
        require(sobrenome.isNotBlank()) {"sobrenome é obrigatorio"}
    }
}