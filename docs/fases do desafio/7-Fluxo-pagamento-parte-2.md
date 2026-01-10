# Começo do fluxo de pagamento - parte 2

---
## Necessidades
Receber também o parâmetro relativo ao carrinho de compras no formulário final.
O json montado pelo cliente relativo ao carrinho tem o seguinte formato:

```
{
    total": decimal,
    "itens":[
       {
        "idLivro":number,
        "quantidade": "number"
       },
       {
        "idLivro":number,
        "quantidade": number
        }
    ]
}
```
---
## Restrição
- o total é não nulo
- o total é maior que zero
- tem pelo menos um item no carrinho
- idLivro é obrigatório e precisa existir
- quantidade é obrigatória
- quantidade é maior que zero
- o total calculado no servidor precisa ser igual ao total enviado

---
## Resultado esperado
- Compra gerada com um status de iniciada
- Status 201 gerado com o endereço de detalhe da compra