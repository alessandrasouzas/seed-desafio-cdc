
# Arquitetura do Projeto – Livro (Hexagonal / Ports & Adapters)

Este documento descreve a arquitetura adotada no projeto, suas camadas, responsabilidades e decisões de design, com foco na feature **Livro**.  
O objetivo é servir como **material de aprendizado**, **referência futura** e **guia de consistência arquitetural**.

---

## 1. Arquitetura Adotada

O projeto segue os princípios da **Arquitetura Hexagonal (Ports & Adapters)**, com separação clara entre:

- **Domínio**
- **Casos de Uso**
- **Portas (Interfaces)**
- **Adapters (Infraestrutura / JPA)**

A regra mais importante:

> **O domínio e os casos de uso NÃO conhecem frameworks, banco de dados ou JPA.**

---

## 2. Visão Geral das Camadas

```

Controller
↓
Use Case
↓
Ports (Interfaces)
↓
Adapters (JPA, External APIs)
↓
Banco de Dados

````

Dependências sempre apontam **para dentro**, nunca para fora.

---

## 3. Camada de Domínio

### 3.1 Entidades de Domínio

As entidades de domínio representam o **modelo de negócio**, não o modelo do banco.

Exemplo: `Livro`

```kotlin
data class Livro(
    val id: Long? = null,
    val titulo: String,
    val resumo: String,
    val sumario: String,
    val preco: Double,
    val numero_Paginas: Int,
    val isbn: String,
    val data_publicacao: LocalDate,
    val categoria: Categoria,
    val autor: Autor
)
````

### Características importantes:

* O domínio **usa objetos**, não IDs soltos.
* `Livro` **possui `Autor` e `Categoria` como objetos**, pois isso representa a realidade do negócio.
* Não existe `Entity`, `@Column`, `@ManyToOne`, etc.

---

## 4. Camada de Use Case (Application)

### 4.1 Responsabilidade do Use Case

O **Use Case** é o **orquestrador da regra de negócio**.

Ele é responsável por:

* Validar regras
* Buscar dados necessários
* Criar o **objeto de domínio completo**
* Decidir **o que** será persistido (não **como**)

> ❌ O Use Case NÃO sabe o que é JPA
> ❌ O Use Case NÃO cria `Entity`
> ❌ O Use Case NÃO acessa banco diretamente

---

### 4.2 Exemplo: `AdicionarLivroUseCase`

```kotlin
class LivroUseCase(
    private val livroRepository: LivroRepositoryPort,
    private val autorRepository: AutorRepositoryPort,
    private val categoriaRepository: CategoriaRepositoryPort
) {

    fun adicionarLivro(novoLivro: NovoLivro) {

        val autor = autorRepository.buscarPorId(novoLivro.autorId)
        val categoria = categoriaRepository.buscarPorId(novoLivro.categoriaId)

        if (
            livroRepository.existePorTitulo(novoLivro.titulo) ||
            livroRepository.existePorIsbn(novoLivro.isbn)
        ) {
            throw Exception("Livro já cadastrado")
        }

        val livro = Livro(
            titulo = novoLivro.titulo,
            resumo = novoLivro.resumo,
            sumario = novoLivro.sumario,
            preco = novoLivro.preco,
            numero_Paginas = novoLivro.numeroPaginas,
            isbn = novoLivro.isbn,
            data_publicacao = novoLivro.dataPublicacao,
            autor = autor,
            categoria = categoria
        )

        livroRepository.salvar(livro)
    }
}
```

### Pontos-chave:

* O Use Case **busca Autor e Categoria apenas uma vez**
* O Use Case **monta o Livro completo**
* O Use Case **não conhece Entity nem JPA**

---

## 5. Ports (Interfaces)

As **Ports** são contratos que o Use Case usa para se comunicar com o mundo externo.

### 5.1 LivroRepositoryPort

```kotlin
interface LivroRepositoryPort {
    fun salvar(livro: Livro)
    fun existePorTitulo(titulo: String): Boolean
    fun existePorIsbn(isbn: String): Boolean
}
```

### Importante:

* O contrato trabalha **com objetos de domínio**
* Nenhuma referência a `Entity`

---

## 6. Adapters (Infraestrutura / JPA)

### 6.1 Responsabilidade do Adapter

O Adapter é responsável por:

* Converter **Domínio → Entity**
* Lidar com JPA, Hibernate, banco
* Resolver detalhes técnicos

> ✅ Aqui é permitido usar `Entity`, `JpaRepository`, `getReferenceById`

---

### 6.2 Conversão Domínio → Entity

Exemplo: `LivroJpaAdapter`

```kotlin
class LivroJpaAdapter(
    private val livroJpaRepository: LivroJpaRepository,
    private val autorJpaRepository: AutorJpaRepository,
    private val categoriaJpaRepository: CategoriaJpaRepository
) : LivroRepositoryPort {

    override fun salvar(livro: Livro) {

        val autorEntity = autorJpaRepository.getReferenceById(livro.autor.id!!)
        val categoriaEntity = categoriaJpaRepository.getReferenceById(livro.categoria.id!!)

        val livroEntity = LivroEntity.toEntity(
            livro = livro,
            autorEntity = autorEntity,
            categoriaEntity = categoriaEntity
        )

        livroJpaRepository.save(livroEntity)
    }
}
```

### Por que buscar `AutorEntity` e `CategoriaEntity` aqui?

* Porque **Entity ≠ Domínio**
* O Use Case **não pode criar Entity**
* `getReferenceById` é detalhe de infraestrutura
* Evita múltiplas queries desnecessárias
* Mantém a arquitetura limpa

---

## 7. Por que NÃO buscar novamente no Use Case?

Pergunta comum levantada durante o desenvolvimento:

> “Se o Use Case já buscou Autor e Categoria, por que o Adapter busca de novo?”

### Resposta arquitetural:

* O Use Case busca **Domínio**
* O Adapter precisa de **Entity**
* São objetos diferentes, com responsabilidades diferentes

Buscar novamente:

* ❌ Não viola arquitetura
* ❌ Não duplica regra de negócio
* ✅ Mantém separação correta de camadas

---

## 8. O que NÃO fazer (Anti-patterns)

❌ Criar `LivroEntity` no Use Case
❌ Passar `AutorEntity` para o domínio
❌ Usar `JpaRepository` no Use Case
❌ Trocar `Categoria` por `categoriaId` no domínio
❌ Misturar regras de negócio com detalhes técnicos

---

## 9. Regra de Ouro

> **Use Case trabalha com Domínio.
> Adapter trabalha com Infraestrutura.
> Entity nunca vaza para dentro.**

---

## 10. Benefícios dessa Arquitetura

* Código mais testável
* Domínio independente de banco
* Facilidade para trocar JPA por outro mecanismo
* Clareza de responsabilidades
* Escalabilidade do projeto

---

## 11. Conclusão

Essa estrutura garante que:

* O **Use Case pense em negócio**
* O **Adapter pense em persistência**
* O **Domínio permaneça limpo e expressivo**

Este arquivo deve ser mantido como referência viva do projeto.