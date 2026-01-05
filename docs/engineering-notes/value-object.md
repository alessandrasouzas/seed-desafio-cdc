# Um **Value Object** é um objeto que:

* ❌ **não tem identidade própria**
* ✅ é definido **apenas pelos seus valores**
* ✅ é **imutável**
* ✅ faz sentido **somente dentro de outro objeto**

📌 Ele **não existe sozinho** no sistema.

Exemplos:

* Dinheiro (valor + moeda)
* Endereço
* Período (data início + data fim)
* Documento (CPF, ISBN)
* **Capa do livro (url)** ← seu caso

---

Um **Value Object, na essência**, é **uma classe separada** que:

* encapsula **um ou mais valores**
* dá **significado de domínio** a esses valores
* protege invariantes (regras)
* **substitui tipos primitivos genéricos** (`String`, `Int`, `Double`)

---

## Diferença prática: Entity vs Value Object

### Entity (Livro)

* Tem **id**
* Vive sozinha
* Pode ser referenciada
* Tem ciclo de vida próprio

### Value Object (Capa)

* ❌ não tem id
* ❌ não existe sem Livro
* ❌ não faz sentido ser buscada sozinha
* ✅ pertence conceitualmente ao Livro

📌 **Se não faz sentido existir sem o dono, é VO.**

---

### Ganhos:

✔ semântica clara
✔ validação centralizada
✔ evolução futura sem refatorar tudo
✔ domínio mais expressivo

---

##  E no banco?

Mesmo usando VO no domínio:

📌 **No banco continua sendo UMA coluna**

```text
livro
------
id
titulo
resumo
capa_url
```

### JPA (exemplo)

```kotlin
@Embeddable
data class Capa(
    @Column(name = "capa_url")
    val url: String
)
```

```kotlin
@Embedded
val capa: Capa?
```

📌 **VO ≠ tabela extra**

---

## Onde fica o Value Object no projeto?

Dentro do **bounded context do Livro**.

Exemplo de package (recomendado):

```text
domain
 └── livro
     ├── Livro.kt
     ├── Capa.kt
     ├── Formato.kt
     └── ISBN.kt
```

✔ coeso
✔ fácil de manter
✔ alinhado com DDD e hexagonal

---

## Quando NÃO usar Value Object?

* ❌ o dado é trivial e nunca terá regra
* ❌ é puramente técnico
* ❌ não pertence conceitualmente ao domínio

## Frase-chave

> **Se o dado não precisa de identidade, mas tem significado, ele é um Value Object.**

### 📌 **Referencia Java**

Em java o value object pode ser declarado pelo uso de *final* ou *record*