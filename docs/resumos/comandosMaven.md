# Comandos essenciais Maven

- mvn clean --> remove diretorios de build (target), limpando artefatos antigos.
- mvn compile --> compila o codigo-fonte do projeto
- mvn test --> executa os testes unitarios
- mvn package --> empacota o codigo compilado
- mvn install --> instala o artefato no repositorio local para uso em outros projetos
- mvn verify --> executa verificações pós-build
- mvn deploy --> publica o artefato em um repositorio remoto (ex.: nexus, artifactory)
- mvn site --> gera documentação do projeto (relatorio, metricas)
- mvn dependecy:tree --> mostra a arvore de dependencias do projeto
- mvn help:effective-pom --> exibe o prom efetivo (com herancas e perfis aplicados)