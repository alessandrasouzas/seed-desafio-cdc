package com.ale.cdc.livraria.application.useCase.command

import com.ale.cdc.livraria.domain.livro.Formato
import java.time.LocalDate

data class CriarLivroCommand (
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val formato: List<Formato>,
    val numeroPaginas: Int,
    val capaUrl: String,
    val isbn: String,
    val subtitulo: String,
    val dataPublicacao: LocalDate,
    val autorId: Long,
    val categoriaId: Long
)