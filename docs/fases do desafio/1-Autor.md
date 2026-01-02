# Cadastro de um novo autor

---

## Necessidades
É necessário cadastrar um novo autor no sistema. Todo autor tem 
- nome, 
- email
- descrição. 
- Também queremos saber o instante exato que ele foi registrado.

---

## Restrições
- O instante não pode ser nulo
- O email é obrigatório
- O email tem que ter formato válido
- O nome é obrigatório
- A descrição é obrigatória e não pode passar de 400 caracteres

---

## Email do autor é único
### Necessidades
O email do autor precisa ser único no sistema
### Resultado esperado
Erro de validação no caso de email duplicado

---

## Resultado esperado
Um novo autor criado e status 200 retornado