## Conceito

**Projection = “recorte” de dados**

Em vez de buscar a entidade inteira do banco, você diz:

> “Quero **só esses campos**.”

Exemplo mental:

* Entidade `LivroEntity` tem 10 campos
* Sua tela precisa só de `id` e `titulo`
* **Projection busca apenas esses 2 campos**

---

## Por que projection existe?

Porque **leitura é o maior gargalo de sistemas reais**.

Se você sempre faz:

```
findAll() → entity completa → domínio → response
```

Você:

* carrega dados inúteis
* cria objetos demais
* força o Hibernate a trabalhar à toa

📌 Projection resolve isso **no banco**, antes de chegar na JVM.

---

## Projection no Spring Data JPA

O Spring cria **implementações dinâmicas** de interfaces para você.

Você define:

```kotlin
interface LivroTituloProjection {
    val id: Long
    val titulo: String
}
```

E o Spring faz:

* `SELECT id, titulo FROM livros`
* mapeia automaticamente
* **sem entity**
* **sem Hibernate Session pesada**

---

## Como o Spring sabe o que buscar?

Por **nome dos atributos**.

Se:

* `LivroEntity` tem `id` e `titulo`
* sua interface tem `val id` e `val titulo`

➡️ Spring faz o match automaticamente.

---

## Por que interface (e não data class)?

### Interface:

* não precisa construtor
* Spring cria proxy dinâmico
* mais leve
* padrão oficial do Spring

### Data class:

* exige `@Query new`
* mais acoplamento
* menos flexível

---

## Onde usar projection (regra prática)

Use projection quando:

* é **listagem**
* leitura massiva
* retorno parcial
* dashboard
* autocomplete
* telas iniciais

❌ Não use quando:

* precisa regra de negócio
* precisa validar invariantes
* precisa alterar estado

---

## Projection vs Domain vs Entity (comparação clara)

| Tipo       | Quando usar          |
| ---------- | -------------------- |
| Entity     | Persistência         |
| Domain     | Regras de negócio    |
| Projection | Leitura performática |
| Response   | Contrato HTTP        |

📌 Projection **não substitui** domínio
Ela substitui **entity + domínio em leitura simples**

---

## Melhor uso em Kotlin (boas práticas)

### ✔ Defina projection pequena

```kotlin
interface LivroTituloProjection {
    val id: Long
    val titulo: String
}
```

### ✔ Use só para leitura

Nada de lógica nela.

### ✔ Converta para Response no adapter

Projection **não sai da infra**.

---

## Fluxo ideal com projection

```
Controller
  ↓
UseCase
  ↓
RepositoryPort
  ↓
RepositoryAdapter
  ↓
JpaRepository (projection)
```

* UseCase continua limpo
* Domínio intacto
* Banco trabalha menos
* API escala melhor

---

## Analogia simples

> Entity é o livro inteiro
> Projection é o índice do livro
> Você não lê o livro todo pra achar o título