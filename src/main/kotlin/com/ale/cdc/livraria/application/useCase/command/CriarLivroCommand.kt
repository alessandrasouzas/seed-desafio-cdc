package com.ale.cdc.livraria.application.useCase.command

import java.time.LocalDate

data class CriarLivroCommand (
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: Double,
    val numeroPaginas: Int,
    val isbn: String,
    val dataPublicacao: LocalDate,
    val autorId: Long,
    val categoriaId: Long
)