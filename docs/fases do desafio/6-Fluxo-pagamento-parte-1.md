# Começo do fluxo de pagamento - parte 1

---
## Necessidades
Uma coisa importante:  Na cdc, você não faz um cadastro e tem suas compras associadas.
Toda vez você coloca seu email, cpf/cnpj etc. Como isso vai ser implementado depende da aplicação.

---
## Os seguintes campos precisam ser preenchidos:
* email
* nome
* sobrenome
* documento(cpf/cnpj)
* endereco
* complemento
* cidade
* pais
* estado(caso aquele pais tenha estado)
* telefone
* cep

---
## Restrição
* email obrigatório e com formato adequado
* nome obrigatório
* sobrenome obrigatório
* documento(cpf/cnpj) obrigatório e só precisa ser um cpf ou cnpj
* endereco obrigatório
* complemento obrigatório
* cidade obrigatório
* país obrigatório
* se o país tiver estados, um estado precisa ser selecionado
* estado(caso aquele pais tenha estado) - apenas se o país tiver cadastro de estados
* telefone obrigatório
* cep é obrigatório 

---
## Resultado esperado
Compra parcialmente gerada, mas ainda não gravada no banco de dados. 
Falta os dados do pedido em si que vão ser trabalhados no próximo cartão.

---
### Observacoes de entendimento do enunciado
- Checkout sem cadastro de usuário
- Dados do comprador vêm do front
- Compra ainda NÃO é persistida
- Backend apenas valida e inicia o fluxo
- Pagamento vem depois