package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.domain.Categoria
import com.ale.cdc.livraria.domain.exception.CategoriaException
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CategoriaUseCaseTest {
    private val repository = mockk<CategoriaRepositoryPort>()
    private val useCase = CategoriaUseCase(repository)

    private fun cat(): Categoria{
        val categoria = Categoria (nome = "suspense")
        return categoria
    }

    @Test
    fun `deve salvar uma categoria com sucesso`() {
        val categoria = cat()

        every { repository.existsByNome(any()) } returns false
        every { repository.salvar(any()) } just Runs //any() -> Teste de fluxo; testa “se passou por aqui”

        useCase.adicionarCategoria(categoria)

        verify(exactly = 1) {
            repository.existsByNome("Suspense")
        }

        verify(exactly = 1) {
            repository.salvar(match { it.nome == "Suspense" }) //match() -> Teste de regra/transformação; testa “se passou certo”
        }
    }


    @Test
    fun `nao deve permitir salvar categoria duplicada`() {
        val categoria = cat()

        every { repository.existsByNome(any()) } returns true
        every { repository.salvar(any()) } just Runs

        val exception = assertThrows<CategoriaException> {
            useCase.adicionarCategoria(categoria)
        }

        assertEquals("Categoria já cadastrada: Suspense", exception.message
        )

        verify(exactly = 1) {
            repository.existsByNome("Suspense")
        }

        verify(exactly = 0) {
            repository.salvar(any())
        }
    }

}