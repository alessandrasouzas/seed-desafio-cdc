package com.ale.cdc.livraria.domain.dadosUsuario

data class Telefone (
    val ddd: String,
    val telefone: String
){
    init {
        require(ddd.isNotBlank() && ddd.length==3) {"ddd é obrigatorio"}
        require(telefone.isNotBlank() && telefone.length==9 && telefone.startsWith("9")) {"telefone é obrigatorio"}
    }
}