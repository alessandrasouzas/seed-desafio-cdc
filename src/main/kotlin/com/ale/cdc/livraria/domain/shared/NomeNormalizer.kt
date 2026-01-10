package com.ale.cdc.livraria.domain.shared

object NomeNormalizer {
    fun normalizaPrimeiraUpper(nome: String): String =
        nome
            .trim()
            .lowercase()
            .replaceFirstChar { it.titlecase() }

    fun normalizaUpperCase(nome: String): String =
        nome
            .trim()
            .uppercase()

}