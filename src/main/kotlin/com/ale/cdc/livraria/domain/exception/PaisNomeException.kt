package com.ale.cdc.livraria.domain.exception

class PaisNomeException (nome: String):
    RuntimeException("Pais já cadastrado: $nome")