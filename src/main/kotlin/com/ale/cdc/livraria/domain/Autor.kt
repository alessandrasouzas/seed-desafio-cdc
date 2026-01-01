package com.ale.cdc.livraria.domain

import java.time.LocalDateTime

data class Autor (
    val id: Long? = null,
    var nome: String,
    var email: String,
    var descricao: String,
    val instante: LocalDateTime = LocalDateTime.now()
) {
    init {
        require(nome.isNotBlank())
        require(email.isNotBlank())
        require(descricao.length <= 400)
    }
}