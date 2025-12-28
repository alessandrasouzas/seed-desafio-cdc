package com.ale.cdc.livraria.domain.exception

class CategoriaException(nome: String) :
    RuntimeException("Categoria já cadastrada: $nome") {
}