package com.ale.cdc.livraria.application.controller.request

data class AutorRequest(
    val nome: String,
    val email: String,
    val descricao: String
)