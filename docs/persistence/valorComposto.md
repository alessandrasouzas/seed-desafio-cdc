
* **`@ElementCollection` + `@Embeddable`**
  Modelagem correta de **valor composto** (`Formato`) sem identidade própria, persistido em **tabela auxiliar** sem virar entidade.


* **Tabela auxiliar (join table)**
  Usada quando há coleção de valores (ex: formatos do livro), mantendo o domínio limpo e o banco normalizado.


* **Mapeamento explícito Domain ↔ Entity**
  Conversão clara nos dois sentidos, evitando acoplamento com JPA e erros de tipo em runtime.