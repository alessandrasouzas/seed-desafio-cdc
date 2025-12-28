package com.ale.cdc.livraria.domain

data class Categoria(
    val nome: String
) {
    init {
        require(nome.isNotBlank()) { "Nome da categoria é obrigatório" }
    }

    //garante que a categoria deve sempre ser criada com o nome normalizado (ex: Suspense) para evitar duplicidade semântica
    companion object {
        fun criar(nome: String): Categoria {
            val normalizado = nome
                .trim()
                .lowercase()
                .replaceFirstChar { it.titlecase() }

            return Categoria(normalizado)
        }
    }
}