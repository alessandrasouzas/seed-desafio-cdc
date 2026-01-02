## Por que ele existe

* Separar **entrada externa (HTTP)** de **regra de negócio**
* Evitar que `Request` vire domínio
* Deixar o `UseCase` independente de Web / JSON

📌 Ele é um **DTO interno da aplicação**.

---

***Origem do conceito:**

Inspirado no **Command Pattern** + **Clean Architecture** (GoF)

Não é o padrão clássico completo, mas vem da mesma ideia:
> encapsular uma ação em um objeto

---

## Em arquiteturas modernas ele aparece como:

| Nome                            | Onde aparece                   |
| ------------------------------- | ------------------------------ |
| **Command**                     | Clean Architecture / DDD       |
| **Use Case Input Model**        | Clean Architecture (Uncle Bob) |
| **Action DTO**                  | Alguns times                   |
| **Request Model (Application)** | CQRS                           |
| **Input Port**                  | Hexagonal (conceitual)         |

📌 **Command é o nome mais comum e aceito atualmente**.

---

## Diferença rápida

| Objeto  | Papel               |
| ------- | ------------------- |
| Request | Representa HTTP     |
| Command | Representa intenção |
| Domain  | Representa regra    |
| Entity  | Representa banco    |

---
