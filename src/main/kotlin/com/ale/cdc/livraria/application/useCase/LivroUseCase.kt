package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.application.useCase.command.CriarLivroCommand
import com.ale.cdc.livraria.domain.Livro
import com.ale.cdc.livraria.domain.exception.TituloException
import org.springframework.stereotype.Service

@Service
class LivroUseCase (
    private val livroRepositoryPort: LivroRepositoryPort
) {

    fun adicionarLivro(cmd: CriarLivroCommand) {

        val livro = Livro(
            titulo = cmd.titulo,
            resumo = cmd.resumo,
            sumario = cmd.sumario,
            preco = cmd.preco,
            numero_Paginas = cmd.numeroPaginas,
            isbn = cmd.isbn,
            data_publicacao = cmd.dataPublicacao,
            autor = null,
            categoria = null
        )

        if(livroRepositoryPort.existePorTitulo(livro.titulo))
            throw TituloException(livro.titulo)
        else livroRepositoryPort.salvar(livro, cmd.autorId, cmd.categoriaId)
    }

}