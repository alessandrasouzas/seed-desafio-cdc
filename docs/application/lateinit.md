# lateinit
Permite declarar uma propriedade **não nula** sem inicializá-la no momento da criação do objeto.

## Quando usar
- Propriedades que **serão inicializadas depois**, mas **antes do uso**
- Injeção de dependência (ex: Spring)
- Testes

### Regras
- Só pode ser usado com `var`
- Não funciona com tipos primitivos (`Int`, `Boolean`, etc.)
- Se acessar antes de inicializar → lança `UninitializedPropertyAccessException`

### Exemplo
```kotlin
lateinit var service: PagamentoService
````

---
## Quando NÃO usar
* Quando o valor pode ser opcional → prefira `?`
* Quando pode ser inicializado no construtor

Use `lateinit` quando o valor é obrigatório, mas a inicialização não acontece no construtor.

---
## Resumo de uso `lateinit`

Usar:
- Controllers (injeção de dependência)
- Services, apenas para dependências (se não usar construtor)

Não usar:
- Objetos de domínio
- Dados opcionais
- Quando pode inicializar no construtor

Regra de ouro:
`lateinit` é para **infraestrutura**, não para **domínio**