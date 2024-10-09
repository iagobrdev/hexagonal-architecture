
# Arquitetura Hexagonal com Spring Boot

Este projeto implementa uma arquitetura hexagonal com **Spring Boot**. A arquitetura hexagonal (também conhecida como Arquitetura de Portas e Adaptadores) permite que a aplicação seja desacoplada das infraestruturas externas, como bancos de dados ou APIs externas, facilitando a manutenção, evolução e testes.

## Estrutura do Projeto

A aplicação está organizada da seguinte maneira:

```
/src
  /main
    /java
      /com/architecture/hexagonal
        /application        
            /controllers    # Contém os controladores REST responsáveis por expor os endpoints da API
        /domain             
            /interfaces      # Define as interfaces que representam abstrações do domínio, como repositórios e serviços
            /services        # Implementações de serviços contendo a lógica de negócios da aplicação
        /infrastructure     
          /config           # Configurações de beans e outras configurações de infraestrutura do Spring
          /entities         # Entidades JPA que mapeiam as tabelas do banco de dados
          /repository       # Repositórios JPA que interagem com o banco de dados e fazem persistência
    /resources
      /db/migration         # Scripts do Flyway para criar e migrar o banco de dados
      application.properties # Configurações da aplicação

```

### Principais Tecnologias Utilizadas

- **Spring Boot**: Framework para construir aplicações Java de forma rápida e produtiva.
- **Spring Data JPA**: Abstração de persistência para interagir com o banco de dados.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.
- **ModelMapper**: Para realizar o mapeamento de objetos entre a camada de domínio e a camada de persistência.
- **Flyway**: Ferramenta para versionamento e migração de banco de dados.
- **JUnit 5 & Mockito**: Frameworks para testes unitários e mock de dependências.

### Pré-requisitos

- **Java 17**: O projeto utiliza o Java 17 como versão mínima.
- **Maven 3.6+**: Para gerenciar dependências e construir o projeto.