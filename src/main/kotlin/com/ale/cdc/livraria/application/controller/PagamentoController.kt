package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.PagamentoRequest
import com.ale.cdc.livraria.application.controller.response.IniciacaoCompraResponse
import com.ale.cdc.livraria.application.useCase.PagamentoUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pagamentos")
class PagamentoController{

    @Autowired
    lateinit var pagamentoUseCase: PagamentoUseCase

    @PostMapping
    fun iniciarPagamento(@RequestBody request: PagamentoRequest): IniciacaoCompraResponse {
        val response = pagamentoUseCase.iniciaCompra(request)
        return response

    //  Alternativa: return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}