package com.ale.cdc.livraria.application.controller.request

import com.ale.cdc.livraria.domain.Livro
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.*
import java.time.LocalDate

data class LivroRequest(

    @field:NotBlank
    val titulo: String,

    @field:NotBlank
    @field:Size(max = 500)
    val resumo: String,

    @field:NotBlank
    val sumario: String,

    @field:DecimalMin("20.0")
    val preco: Double,

    @field:Min(100)
    val numeroPaginas: Int,

    @field:NotBlank
    val isbn: String,

    @field:NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    val dataPublicacao: LocalDate,

    @field:NotBlank
    val categoria: String,

    @field:NotBlank
    val autor: String

){

    init {
        require(titulo.isNotBlank()) { "Titulo é obrigatório" }
        require(resumo.isNotBlank() && resumo.length <= 500) { "Resumo é obrigatório" }
        require(preco>=20) { "Preço é obrigatório" }
        require(numeroPaginas>=100) { "Numero de paginas é obrigatório" }
        require(isbn.isNotBlank()) { "ISBN é obrigatório" }
        require(!dataPublicacao.isAfter(LocalDate.now().plusYears(1))) {"Data de publicação não pode ser superior a 1 ano no futuro" }
        requireNotNull(categoria)
        requireNotNull(autor)
    }

    fun toDomain(): Livro {
        return Livro(
            titulo = titulo,
            sumario = sumario,
            resumo = resumo,
            numero_Paginas = numeroPaginas,
            isbn = isbn,
            preco = preco,
            data_publicacao = dataPublicacao,
            categoria = categoria,
            autor = autor
        )
    }
}
