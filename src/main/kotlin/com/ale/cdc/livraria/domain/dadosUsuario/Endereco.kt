package com.ale.cdc.livraria.domain.dadosUsuario

data class Endereco (
    val endereço: String,
    val complemento: String,
    val cidade: String,
    val cep: String
){
    init {
        require(endereço.isNotBlank()) {"endereço é obrigatorio"}
        require(complemento.isNotBlank()) {"complemento é obrigatorio"}
        require(cidade.isNotBlank()) {"cidade é obrigatorio"}
        require(cep.isNotBlank()) {"cep é obrigatorio"}
    }
}