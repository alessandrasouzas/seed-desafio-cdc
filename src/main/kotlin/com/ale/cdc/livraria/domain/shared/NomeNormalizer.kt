package com.ale.cdc.livraria.domain.shared

object NomeNormalizer {
    fun normalizar(nome: String): String =
        nome
            .trim()
            .lowercase()
            .replaceFirstChar { it.titlecase() }
}