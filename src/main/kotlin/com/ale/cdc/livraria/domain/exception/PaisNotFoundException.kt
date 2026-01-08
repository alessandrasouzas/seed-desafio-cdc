package com.ale.cdc.livraria.domain.exception

class PaisNotFoundException (id: Long):
    RuntimeException("Pais não foi cadastrado: $id")