package com.ale.cdc.livraria.domain.exception

class TituloException(titulo: String) :
    RuntimeException("Titulo já foi adicionado: $titulo") {
}