package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.useCase.PaisUseCase
import com.ale.cdc.livraria.application.controller.request.PaisRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/paises")
class PaisController (
    private val paisUseCase: PaisUseCase
){

    @PostMapping
    fun adicionarPais(@RequestBody @Valid request: PaisRequest): ResponseEntity<Void> {
        paisUseCase.adicionarPais(request.toDomain())
        return ResponseEntity.ok().build()
    }

}