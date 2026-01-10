package com.ale.cdc.livraria.domain

import com.ale.cdc.livraria.domain.dadosUsuario.*

data class IniciaPagamento (
    val nome: Nome,
    val email: Email,
    val documento: Documento,
    val endereco: Endereco,
    val pais: Pais,
    val estado: Estado?,
    val telefone: Telefone
)