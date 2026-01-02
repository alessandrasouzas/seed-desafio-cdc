package com.ale.cdc.livraria.domain.exception

class AutorNotFoundException (id: Long) :
    RuntimeException("Codigo de Autor não encontrado: $id") {
}