package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.controller.response.LivroResponse
import com.ale.cdc.livraria.application.controller.response.LivroTituloResponse
import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.application.useCase.command.CriarLivroCommand
import com.ale.cdc.livraria.domain.livro.Livro
import com.ale.cdc.livraria.domain.exception.AutorNotFoundException
import com.ale.cdc.livraria.domain.exception.CategoriaNotFoundException
import com.ale.cdc.livraria.domain.exception.TituloException
import com.ale.cdc.livraria.domain.livro.Isbn
import org.springframework.stereotype.Service
import kotlin.collections.List

@Service
class LivroUseCase (
    private val livroRepositoryPort: LivroRepositoryPort,
    private val autorRepositoryPort: AutorRepositoryPort,
    private val categoriaRepositoryPort: CategoriaRepositoryPort
) {

    fun adicionarLivro(cmd: CriarLivroCommand) {

        if(!autorRepositoryPort.existePorId(cmd.autorId))
            throw AutorNotFoundException(cmd.autorId)

        if(!categoriaRepositoryPort.existePorId(cmd.categoriaId))
            throw CategoriaNotFoundException(cmd.categoriaId)

        val livro = Livro(
            titulo = cmd.titulo,
            resumo = cmd.resumo,
            sumario = cmd.sumario,
            formatos = cmd.formato   ,
            numeroPaginas = cmd.numeroPaginas,
            isbn = Isbn(cmd.isbn),
            dataLancamento = cmd.dataPublicacao,
            autor = null,
            categoria = null
        )

        if(livroRepositoryPort.existePorTitulo(livro.titulo))
            throw TituloException(livro.titulo)
        else livroRepositoryPort.salvar(livro, cmd.autorId, cmd.categoriaId)
    }

    fun buscarLivros(): List<LivroResponse> {
        return livroRepositoryPort.buscarLivros()
            .map { LivroResponse.toResponse(it) }
    }

    fun buscarTitulos(): List<LivroTituloResponse> {
        return livroRepositoryPort.buscarTitulos()
            .map {
                LivroTituloResponse(
                    id = it.id,
                    titulo = it.titulo
                )
            }
    }
}