package com.ale.cdc.livraria.domain.exception

class LivroNotFoundException(id: String?) :
    RuntimeException("Livro não foi encontrado: $id?") {
}