# Cadastro de uma categoria

## Necessidades
- Toda categoria precisa de um nome

---

## Restrições
- O nome é obrigatório
- O nome não pode ser duplicado

---

## Resultado esperado
Uma nova categoria cadastrada no sistema e status 200 retorno
Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação


---

## Criação de um validador customizado genérico para verificar unicidade de determinada informação
Tanto para o cadastro do autor quanto para o cadastro da categoria, 
foi necessário realizar uma validação de valor único no sistema. 
Neste caso, só muda um detalhe da query que estamos executando para fazer a verificação.