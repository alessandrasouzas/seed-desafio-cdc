package com.ale.cdc.livraria.domain.dadosUsuario

class Documento (
    val valor: String
) {
    init {
        require(valor.all { it.isDigit() }) {"Documento deve conter apenas números"}
        require(valor.length == 11 || valor.length == 14) {"Documento deve ser CPF ou CNPJ"}
    }

}