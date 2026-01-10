package com.ale.cdc.livraria.domain.dadosUsuario

data class Email (
    val email: String
){
    init {
        require(email.isNotBlank() && email.contains("@")) {"email é obrigatorio"}
    }
}