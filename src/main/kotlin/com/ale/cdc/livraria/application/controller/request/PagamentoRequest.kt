package com.ale.cdc.livraria.application.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class PagamentoRequest (

    @field:NotBlank
    val nome: String,
    @field:NotBlank
    val sobrenome: String,
    @field:NotBlank
    val email: String,
    @field:NotBlank
    @field:Pattern(
        regexp = "\\d+",
        message = "Documento deve conter apenas números"
    )
    val documento: String,
    @field:NotBlank
    val endereco: String,
    @field:NotBlank
    val complemento: String,
    @field:NotBlank
    val cidade: String,
    @field:NotBlank
    val cep: String,
    @field:NotBlank
    val pais: String,
    val estado: String?,
    @field:NotBlank
    @field:Pattern(
        regexp = "\\d+",
        message = "Documento deve conter apenas números"
    )
    val telefone: String,
    @field:NotBlank
    @field:Pattern(
        regexp = "\\d+",
        message = "Documento deve conter apenas números"
    )
    val ddd: String

)