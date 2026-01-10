package com.ale.cdc.livraria.application.controller.response

import com.ale.cdc.livraria.domain.StatusCompra

data class IniciacaoCompraResponse (
    val idTemporarioCompra: String,
    val statusCompra: StatusCompra
)