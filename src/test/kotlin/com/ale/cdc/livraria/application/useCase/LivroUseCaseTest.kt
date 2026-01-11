package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.controller.response.LivroDetalheResponse
import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.application.useCase.command.CriarLivroCommand
import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.exception.AutorNotFoundException
import com.ale.cdc.livraria.domain.exception.CategoriaNotFoundException
import com.ale.cdc.livraria.domain.exception.TituloException
import com.ale.cdc.livraria.domain.livro.*
import com.ale.cdc.livraria.infrastructure.persistence.jpa.projection.LivroTituloProjection
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.Long
import kotlin.test.assertEquals

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
        formato = listOf(
            Formato(
                tipo = TipoFormato.IMPRESSO,
                preco = BigDecimal("50.00")
            )
        ),
        numeroPaginas = 200,
        isbn = "123-456",
        capaUrl = "/img/capa.png",
        dataPublicacao = LocalDate.now().plusDays(2),
        autorId = 1L,
        categoriaId = 2L,
        subtitulo = "essencial"
    )

    private fun livro() = Livro (
        id = 1L,
        titulo = "Clean Architecture",
        resumo = "Resumo válido",
        sumario = "Sumário válido",
        formatos = listOf(
            Formato(
                tipo = TipoFormato.IMPRESSO,
                preco = BigDecimal("50.00")
            )
        ),
        numeroPaginas = 200,
        isbn = Isbn("123-456"),
        capaLivro = UrlCapa("/img/capa.png"),
        dataLancamento = LocalDate.now().plusDays(2),
        autor = Autor(nome = "Uncle bob", email = "bob@uncle.com", descricao = "tio bob"),
        categoria = Categoria(nome = "Programação"),
        subtitulo = "essencial"
    )

    @Test
    fun `deve salvar livro com sucesso`() {
        val cmd = cmd()

        every { autorRepository.existePorId(any())} returns true
        every { categoriaRepository.existePorId(any()) } returns true
        every { livroRepository.existePorTitulo(any()) } returns false
        every { livroRepository.salvar(any(), any(), any()) } just Runs

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

        every { autorRepository.existePorId(any()) } returns true
        every { categoriaRepository.existePorId(any()) } returns true
        every { livroRepository.existePorTitulo(any()) } returns true

        assertThrows<TituloException> {
            useCase.adicionarLivro(cmd)
        }

        verify(exactly = 0) {
            livroRepository.salvar(any(), any(), any())
        }
    }

    @Test
    fun `deve buscar livros com sucesso`(){
        val livro = livro()

        every { livroRepository.buscarLivros() } returns listOf(livro)
        val resultado = useCase.buscarLivros()

        assertEquals("Clean Architecture", resultado.first().titulo)
    }

    @Test
    fun `deve buscar titulos com sucesso`() {
        //Arrange
        val projection = mockk<LivroTituloProjection>()

        every { projection.id } returns 1L
        every { projection.titulo } returns "Livro A"
        every { livroRepository.buscarTitulos() } returns listOf(projection)

        //Act
        val resultado = useCase.buscarTitulos()

        assertEquals(1, resultado.size)
        assertEquals(1L, resultado.first().id)
        assertEquals("Livro A", resultado.first().titulo)
    }

    @Test
    fun `nao deve permitir salvar livro com categoria nao encontrada`() {
        val cmd = cmd()

        every { autorRepository.existePorId(cmd.autorId) } returns true
        every { categoriaRepository.existePorId(cmd.categoriaId) } returns false

        val exception = assertThrows<CategoriaNotFoundException> {
            useCase.adicionarLivro(cmd)
        }

        assertEquals("Codigo de Categoria não encontrada: 2", exception.message)

        verify(exactly = 0) {
            livroRepository.salvar(any(), any(), any())
        }
    }

    @Test
    fun `nao buscar livro detalhe com sucesso`() {
        val livro = livro()

        every{livroRepository.buscarLivroDetalhe(livro.id!!)} returns livro

        val resultado = useCase.buscarDetalheLivro(livro.id!!)

        assertEquals("Clean Architecture", resultado.titulo)
        assertEquals("/img/capa.png", resultado.capaLivro)

    }

}