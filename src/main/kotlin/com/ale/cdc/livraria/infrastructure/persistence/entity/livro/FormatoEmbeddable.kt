package com.ale.cdc.livraria.infrastructure.persistence.entity.livro

import com.ale.cdc.livraria.domain.livro.TipoFormato
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal

@Embeddable
data class FormatoEmbeddable(

    @Enumerated(EnumType.STRING)
    val tipo: TipoFormato,

    @Column(precision = 10, scale = 2)
    val preco: BigDecimal
)
