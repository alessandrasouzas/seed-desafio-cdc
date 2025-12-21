package com.ale.cdc.livraria.domain

import java.time.LocalDateTime

data class Autor (
    var nome: String,
    var email: String,
    var descricao: String,
    val instante: LocalDateTime = LocalDateTime.now()
) {
    init {
        println("Criando novo autor!")
        require(nome.isNotBlank())
        require(email.isNotBlank())
        require(descricao.length <= 400)
    }
}