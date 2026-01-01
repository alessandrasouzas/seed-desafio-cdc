package com.ale.cdc.livraria.infrastructure.persistence.adapter

import com.ale.cdc.livraria.application.port.AutorRepositoryPort
import com.ale.cdc.livraria.application.port.CategoriaRepositoryPort
import com.ale.cdc.livraria.application.port.LivroRepositoryPort
import com.ale.cdc.livraria.domain.Livro
import com.ale.cdc.livraria.infrastructure.persistence.entity.LivroEntity
import com.ale.cdc.livraria.infrastructure.persistence.jpa.LivroRepositoryJpa
import org.springframework.stereotype.Component

@Component
class LivroRepositoryAdapter (
    private val jpaRepository: LivroRepositoryJpa,
    private val jpaAutorPort: AutorRepositoryPort,
    private val jpaCategoriaPort: CategoriaRepositoryPort
): LivroRepositoryPort {

    override fun salvar(livro: Livro, autorId: Long, categoriaId: Long) {

        val autorEntity = jpaAutorPort.buscaPorId(autorId)
        val categoriaEntity = jpaCategoriaPort.buscaPorId(categoriaId)

        val entity = LivroEntity.toEntity(livro, autorEntity, categoriaEntity)
        jpaRepository.save(entity)
    }

    override fun existePorTitulo(titulo: String): Boolean {
        return jpaRepository.existsByTitulo(titulo)
    }
}