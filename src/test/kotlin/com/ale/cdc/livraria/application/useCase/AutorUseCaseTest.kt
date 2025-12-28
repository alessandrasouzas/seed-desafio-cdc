package com.ale.cdc.livraria.application.useCase

import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.domain.Autor
import com.ale.cdc.livraria.domain.exception.EmailException
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AutorUseCaseTest {
    private val repository = mockk<AutorRepositoryPort>()
    private val useCase = AutorUseCase(repository)

    private fun autorValido() = Autor(
        nome = "Maria",
        email = "maria@email.com",
        descricao = "Autora teste"
    )

    @Test
    fun `deve salvar autor quando email nao existe`() {
        val autor = autorValido()

        every { repository.existePorEmail(autor.email) } returns false
        every { repository.salvar(autor) } just Runs

        useCase.adicionarAutor(autor)

        verify(exactly = 1) {
            repository.salvar(autor)
        }
        verify(exactly = 1) {
            repository.existePorEmail(autor.email)
        }
    }

    @Test
    fun `deve lançar excecao quando email ja existe`() {
        val autor = autorValido()

        every { repository.existePorEmail(autor.email) } returns true

        val exception = assertThrows<EmailException> {
            useCase.adicionarAutor(autor)
        }

        assertEquals("Email já cadastrado: maria@email.com", exception.message)

    }

    @Test
    fun `deve falhar ao criar autor com email invalido`() {
        assertThrows<IllegalArgumentException> {
            Autor(
                nome = "Maria",
                email = "",
                descricao = "Teste"
            )
        }
    }


}