package com.ale.cdc.livraria.application.controller.request

import com.ale.cdc.livraria.application.useCase.command.CriarLivroCommand
import com.ale.cdc.livraria.domain.livro.Formato
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.*
import java.time.LocalDate

data class LivroRequest(

    @field:NotBlank
    val titulo: String,

    @field:NotBlank
    @field:Size(max = 500)
    val resumo: String,

    val capaUrl: String,

    @field:NotBlank
    val sumario: String,

    val formatos: List<Formato>,

    @field:Min(100)
    val numeroPaginas: Int,

    @field:NotBlank
    val isbn: String,

    @field:NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    val dataPublicacao: LocalDate,

    @field:NotNull
    @field:Positive
    val autorId: Long,

    @field:NotNull
    @field:Positive
    val categoriaId: Long
){

    init {
        require(titulo.isNotBlank()) { "Titulo é obrigatório" }
        require(resumo.isNotBlank() && resumo.length <= 500) { "Resumo é obrigatório" }
        require(numeroPaginas>=100) { "Numero de paginas é obrigatório" }
        require(dataPublicacao.isAfter(LocalDate.now().plusDays(1))) {"Data de publicação deve ser superior a 1 dia no futuro" }
        requireNotNull(categoriaId) {"Categoria é obrigatória"}
        requireNotNull(autorId) {"Autor é obrigatório"}
    }

    fun toCommand() = CriarLivroCommand(
        titulo = titulo,
        resumo = resumo,
        sumario = sumario,
        formato = formatos,
        capaUrl = capaUrl,
        numeroPaginas = numeroPaginas,
        isbn = isbn,
        dataPublicacao = dataPublicacao,
        autorId = autorId,
        categoriaId = categoriaId
    )

}
