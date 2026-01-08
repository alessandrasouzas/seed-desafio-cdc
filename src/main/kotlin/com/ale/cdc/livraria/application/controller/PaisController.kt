package com.ale.cdc.livraria.application.controller

import com.ale.cdc.livraria.application.controller.request.EstadoRequest
import com.ale.cdc.livraria.application.useCase.PaisUseCase
import com.ale.cdc.livraria.application.controller.request.PaisRequest
import com.ale.cdc.livraria.application.useCase.command.CriarEstadoCommand
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
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

    @PostMapping("/{paisId}/estados")
    fun adicionarEstado(@RequestBody @Valid request: EstadoRequest,
                        @PathVariable paisId: Long): ResponseEntity<Void> {
        val cmd = CriarEstadoCommand(request.nome, paisId)
        paisUseCase.adicionarEstado(cmd)

        return ResponseEntity.ok().build()
    }
}