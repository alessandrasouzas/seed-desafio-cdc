## HTTP – conceitos essenciais

### 1️⃣ **Method (Verbo HTTP)**

Define a **intenção** da requisição.

* `GET` → buscar dados
* `POST` → criar recurso
* `PUT` → atualizar recurso inteiro
* `PATCH` → atualizar parcialmente
* `DELETE` → remover recurso

---

### 2️⃣ **Path**

Identifica o **recurso**.

```
/livros
/livros/10
/autores/5/livros
```

---

### 3️⃣ **Query Params**

Parâmetros **opcionais** para filtro, ordenação ou variação da resposta.

```
/livros?view=titulo
/livros?pagina=1&tamanho=10
```

➡️ Não mudam o recurso, apenas **como ele é retornado**.

---

### 4️⃣ **Headers**

Metadados da requisição/resposta.

* `Accept` → formato esperado (JSON, XML)
* `Content-Type` → formato enviado
* `Authorization` → autenticação
* `Cache-Control` → cache
* `User-Agent` → cliente

---

### 5️⃣ **Body**

Dados enviados ao servidor.

* Usado em `POST`, `PUT`, `PATCH`
* Normalmente JSON

```json
{
  "titulo": "Clean Architecture"
}
```

---

### 6️⃣ **Status Code**

Resultado da operação.

* `200 OK`
* `201 Created`
* `400 Bad Request`
* `404 Not Found`
* `409 Conflict`
* `500 Internal Server Error`

---

### 7️⃣ **Response**

Composto por:

* **Status**
* **Headers**
* **Body** (opcional)

---

### 8️⃣ **Idempotência**

* `GET`, `PUT`, `DELETE` → repetir não muda o resultado
* `POST` → pode criar múltiplos recursos

---

### 9️⃣ **Contrato**

Controller expõe um **contrato HTTP**:

* rota
* verbo
* formato de entrada
* formato de saída

Mudanças aqui impactam o **cliente**, não o domínio.