# API de Gerenciamento de Tarefas (To-Do List)

## 📖 Sobre o Projeto

Esta é uma API RESTful completa para gerenciamento de tarefas, desenvolvida como parte do desafio final do bootcamp de backend. A aplicação permite que usuários se cadastrem, autentiquem e gerenciem suas próprias listas de tarefas de forma segura.

O foco do projeto foi construir uma arquitetura de microsserviço robusta, segura e escalável, seguindo as melhores práticas de desenvolvimento com o ecossistema Spring.

---

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias e conceitos:

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.x
* **Segurança:** Spring Security com autenticação stateless baseada em Token **JWT**.
* **Persistência de Dados:** Spring Data JPA com Hibernate.
* **Banco de Dados:** PostgreSQL
* **Validação:** Jakarta Bean Validation
* **Documentação:** Springdoc OpenAPI 3 (Swagger UI)
* **Build Tool:** Maven

---

## ✨ Features Principais

* **Autenticação e Autorização:** Sistema de segurança completo com registro, login e proteção de endpoints baseada em papéis (Roles).
* **CRUD de Tarefas:** Operações completas de Criar, Ler, Atualizar e Deletar tarefas.
* **Segurança de Dados:** Um usuário só pode acessar e manipular as suas próprias tarefas.
* **Tratamento de Exceções:** Handler global para retornar mensagens de erro padronizadas e amigáveis.
* **Documentação Interativa:** Geração automática da documentação da API com Swagger UI.

---

## 🚀 Como Rodar o Projeto Localmente

Siga os passos abaixo para executar a aplicação no seu ambiente de desenvolvimento.

### Pré-requisitos

* Java JDK 17 ou superior.
* Maven 3.8 ou superior.
* Uma instância do PostgreSQL rodando.
* Uma ferramenta para testar APIs, como o [Postman](https://www.postman.com/downloads/).

### Passos para a Instalação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/pedrohgmello/api-board-de-tarefas-crud.git
    cd seu-repositorio
    ```

2.  **Configure o Banco de Dados:**
    * Crie um novo banco de dados no seu PostgreSQL (ex: `tarefas_db`).
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as seguintes propriedades com as suas credenciais do banco:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/tarefas_db
        spring.datasource.username=seu_usuario_postgres
        spring.datasource.password=sua_senha_postgres
        ```

3.  **Configure o Segredo JWT:**
    * Ainda no `application.properties`, altere a chave secreta do JWT para um valor seguro e complexo:
        ```properties
        api.security.token.secret=seu-segredo-super-secreto-e-longo
        ```

4.  **Execute a Aplicação:**
    * Use o Maven Wrapper para compilar e rodar o projeto:
        ```bash
        ./mvnw spring-boot:run
        ```
    * A API estará disponível em `http://localhost:8080`.

---

## 📝 Endpoints da API

A documentação interativa completa está disponível via Swagger UI após iniciar a aplicação:
* **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Autenticação

#### `POST api/auth/register`
* **Descrição:** Registra um novo usuário no sistema.
* **Corpo da Requisição (JSON):**
    ```json
    {
      "login": "novo.usuario",
      "senha": "senhaForte123"
    }
    ```
* **Resposta de Sucesso (201 Created):**
    ```json
    {
      "id": 1,
      "login": "novo.usuario",
      "roles": ["ROLE_USER"]
    }
    ```

#### `POST api/auth/login`
* **Descrição:** Autentica um usuário e retorna um token JWT.
* **Corpo da Requisição (JSON):**
    ```json
    {
      "login": "novo.usuario",
      "senha": "senhaForte123"
    }
    ```
* **Resposta de Sucesso (200 OK):**
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

### Tarefas (Endpoints Protegidos)

*Atenção: Todas as requisições para os endpoints abaixo devem incluir o cabeçalho `Authorization: Bearer <seu_token_jwt>`.*

#### `POST /api/tarefas`
* **Descrição:** Cria uma nova tarefa para o usuário autenticado.
* **Corpo da Requisição (JSON):**
    ```json
    {
      "descricao": "Finalizar o desafio do bootcamp"
    }
    ```
* **Resposta de Sucesso (201 Created):** Retorna a tarefa recém-criada.

#### `GET /api/tarefas`
* **Descrição:** Lista todas as tarefas do usuário autenticado.
* **Resposta de Sucesso (200 OK):** Retorna um array de tarefas.
    ```json
    [
      {
        "id": 1,
        "descricao": "Finalizar o desafio do bootcamp",
        "concluida": false,
        "dataDeCriacao": "..."
      }
    ]
    ```

#### `PUT /api/tarefas/{id}`
* **Descrição:** Atualiza uma tarefa existente do usuário autenticado.
* **Corpo da Requisição (JSON):**
    ```json
    {
      "descricao": "Fazer o deploy da API no Render",
      "concluida": true
    }
    ```
* **Resposta de Sucesso (200 OK):** Retorna a tarefa atualizada.

#### `DELETE /api/tarefas/{id}`
* **Descrição:** Deleta uma tarefa do usuário autenticado.
* **Resposta de Sucesso (204 No Content):** Nenhum corpo na resposta.

---

Feito com ❤️ por [Pedro Mello]
