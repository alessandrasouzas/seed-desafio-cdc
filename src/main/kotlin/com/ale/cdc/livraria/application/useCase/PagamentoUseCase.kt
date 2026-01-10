package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.controller.request.PagamentoRequest
import com.ale.cdc.livraria.application.controller.response.IniciacaoCompraResponse
import com.ale.cdc.livraria.domain.StatusCompra
import com.ale.cdc.livraria.domain.exception.DocumentoInvalidoException
import org.springframework.stereotype.Service

@Service
class PagamentoUseCase {

    fun iniciaCompra(request: PagamentoRequest): IniciacaoCompraResponse {

        if(request.documento.length!=11 || request.documento.length!=14)
            throw DocumentoInvalidoException()

        if(validarCpf(request.documento) || validarCnpj(request.documento))
            return IniciacaoCompraResponse(idTemporarioCompra = "123", statusCompra = StatusCompra.INICIADA)
        throw DocumentoInvalidoException()
    }


    /* ===== CPF ===== */
    private fun validarCpf(cpf: String): Boolean {
        if (cpf.all { it == cpf[0] }) return false

        val digitos = cpf.map { it.digitToInt() }

        val soma1 = (0..8).sumOf { (10 - it) * digitos[it] }
        val dv1 = ((soma1 * 10) % 11).let { if (it == 10) 0 else it }
        if (dv1 != digitos[9]) return false

        val soma2 = (0..9).sumOf { (11 - it) * digitos[it] }
        val dv2 = ((soma2 * 10) % 11).let { if (it == 10) 0 else it }
        if (dv2 != digitos[10]) return false
        return true
    }

    /* ===== CNPJ ===== */
    private fun validarCnpj(cnpj: String): Boolean {
        if (cnpj.all { it == cnpj[0] }) return false

        val digitos = cnpj.map { it.digitToInt() }

        val pesos1 = intArrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val soma1 = pesos1.indices.sumOf { pesos1[it] * digitos[it] }
        val dv1 = (soma1 % 11).let { if (it < 2) 0 else 11 - it }
        if (dv1 != digitos[12]) return false

        val pesos2 = intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val soma2 = pesos2.indices.sumOf { pesos2[it] * digitos[it] }
        val dv2 = (soma2 % 11).let { if (it < 2) 0 else 11 - it }
        if (dv2 != digitos[13]) return false
        return true
    }

}