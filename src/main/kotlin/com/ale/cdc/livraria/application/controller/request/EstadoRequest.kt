package com.ale.cdc.livraria.application.controller.request

import jakarta.validation.constraints.NotBlank

data class EstadoRequest (
    @field:NotBlank
    val nome: String
)