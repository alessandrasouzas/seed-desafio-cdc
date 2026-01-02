package com.ale.cdc.livraria.domain.exception

class CategoriaNotFoundException (id: Long) :
    RuntimeException("Codigo de Categoria não encontrada: $id") {

    }