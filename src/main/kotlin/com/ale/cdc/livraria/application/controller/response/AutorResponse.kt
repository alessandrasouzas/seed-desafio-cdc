package com.ale.cdc.livraria.application.controller.response

import java.time.LocalDateTime

data class AutorResponse(
    val id: Long,
    var nome: String,
    var email: String,
    var descricao: String,
    val instante: LocalDateTime = LocalDateTime.now()
) {
}