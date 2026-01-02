package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.application.useCase.command.CriarLivroCommand
import com.ale.cdc.livraria.domain.exception.AutorNotFoundException
import com.ale.cdc.livraria.domain.exception.TituloException
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import java.time.LocalDate

class LivroUseCaseTest {

    private val livroRepository = mockk<LivroRepositoryPort>()
    private val autorRepository = mockk<AutorRepositoryPort>()
    private val categoriaRepository = mockk<CategoriaRepositoryPort>()

    private val useCase = LivroUseCase(
        livroRepository,
        autorRepository,
        categoriaRepository
    )

    private fun cmd() = CriarLivroCommand(
        titulo = "Clean Architecture",
        resumo = "Resumo válido",
        sumario = "Sumário válido",
        preco = 50.0,
        numeroPaginas = 200,
        isbn = "123-456",
        dataPublicacao = LocalDate.now().plusDays(2),
        autorId = 1L,
        categoriaId = 2L
    )

    @Test
    fun `deve salvar livro com sucesso`() {
        val cmd = cmd()

        every { autorRepository.existePorId(cmd.autorId) } returns true
        every { categoriaRepository.existePorId(cmd.categoriaId) } returns true
        every { livroRepository.existePorTitulo(cmd.titulo) } returns false
        every { livroRepository.salvar(any(), cmd.autorId, cmd.categoriaId) } just Runs

        useCase.adicionarLivro(cmd)

        verify(exactly = 1) {
            livroRepository.salvar(any(), cmd.autorId, cmd.categoriaId)
        }
    }

    @Test
    fun `nao deve permitir salvar livro quando autor nao existe`() {
        val cmd = cmd()

        every { autorRepository.existePorId(cmd.autorId) } returns false

        assertThrows<AutorNotFoundException> {
            useCase.adicionarLivro(cmd)
        }

        verify(exactly = 0) {
            livroRepository.salvar(any(), any(), any())
        }
    }

    @Test
    fun `nao deve permitir salvar livro com titulo duplicado`() {
        val cmd = cmd()

        every { autorRepository.existePorId(cmd.autorId) } returns true
        every { categoriaRepository.existePorId(cmd.categoriaId) } returns true
        every { livroRepository.existePorTitulo(cmd.titulo) } returns true

        assertThrows<TituloException> {
            useCase.adicionarLivro(cmd)
        }

        verify(exactly = 0) {
            livroRepository.salvar(any(), any(), any())
        }
    }

}