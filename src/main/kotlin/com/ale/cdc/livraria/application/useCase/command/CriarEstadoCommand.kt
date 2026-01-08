package com.ale.cdc.livraria.application.useCase.command

data class CriarEstadoCommand(
    val nome: String,
    val paisId: Long
)
