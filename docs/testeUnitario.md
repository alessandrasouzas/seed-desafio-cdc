# Testes Unitários — Resumo Essencial

## O que é teste unitário
- Testa **uma unidade isolada** (classe ou método)
- Não acessa banco, rede ou filesystem
- Executa rápido e valida regras de negócio

---

## Estrutura básica (AAA)

### 1️⃣ Arrange (Preparação)
- Cria os objetos
- Configura mocks
- Define comportamentos esperados

Ex:
```kotlin
val categoria = Categoria("Suspense")
every { repository.existsByNome(any()) } returns false
````

---

### 2️⃣ Act (Ação)

* Executa **apenas o método testado**

Ex:

```kotlin
useCase.adicionarCategoria(categoria)
```

---

### 3️⃣ Assert (Verificação)

* Valida o resultado ou exceção

Ex:

```kotlin
assertThrows<CategoriaException> { ... }
assertEquals("Suspense", categoria.nome)
```

---

## Mocks (MockK)

### `every { ... }`

* Define **o que acontece quando o método mockado é chamado**
* Simula retorno ou comportamento

```kotlin
every { repository.salvar(any()) } just Runs
```

---

### `verify { ... }`

* Verifica **se e quantas vezes** um método foi chamado
* Confirma **comportamento**, não estado

```kotlin
verify(exactly = 1) { repository.salvar(any()) }
verify(exactly = 0) { repository.salvar(any()) }
```

📌 Use `verify` quando:

* A lógica depende de **interações**
* Você quer garantir que algo **não foi executado**

---

## any() vs match()

* `any()` → aceita qualquer valor
* `match { }` → valida condição específica

```kotlin
match { it.nome == "Suspense" }
```

---

## Boas práticas essenciais

* Teste **UseCases**, não Controllers
* 1 teste = 1 cenário
* Nome do teste deve explicar o comportamento
* Não teste framework (Spring, JPA, Jackson)
* Prefira **estado + comportamento**, não implementação

---

## Tipos básicos de testes

* ✅ Sucesso (fluxo feliz)
* ❌ Falha (exceção esperada)
* ⚠️ Borda (valores limites / normalização)

---

## Erros comuns

* Mockar demais
* Testar JPA em teste unitário
* Misturar Arrange com Act
* Testes frágeis (dependem de ordem)

---

## Próximos passos recomendados

* Testes de borda mais ricos
* Testes de mapeamento (Domain ↔ Entity)
* Testes de integração (@SpringBootTest)
* Property-based testing
* Cobertura sem obsessão (foco em regra)

---