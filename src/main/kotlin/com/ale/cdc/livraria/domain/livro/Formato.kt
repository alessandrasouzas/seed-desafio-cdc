package com.ale.cdc.livraria.domain.livro

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal

@Embeddable
data class Formato (
    val preco: BigDecimal,

    @Enumerated(EnumType.STRING)
    val tipo: TipoFormato
){
    init{
        require(preco>= BigDecimal.valueOf(20.00)) { "Preço é obrigatório" }
    }

}