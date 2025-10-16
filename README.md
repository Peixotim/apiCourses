

# 🚀 API de Cadastro de Cursos — Spring Boot + JWT + Auth0 + Docker

![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?style=for-the-badge)
![Security](https://img.shields.io/badge/Spring%20Security-JWT-blue?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17.6-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

---

## 🧠 Sobre o projeto

A **API de Cadastro de Cursos** é um projeto completo desenvolvido em **Spring Boot 3** com autenticação **JWT (JSON Web Token)** e integração com **Auth0**.  
Ela foi projetada para ser uma base sólida e escalável de backend, aplicando boas práticas de **arquitetura REST**, **segurança**, **auditoria** e **tratamento global de exceções**.

Essa aplicação é ideal para cenários de cadastro e gerenciamento de **faculdades, cursos e técnicos**, com controle de **usuários e permissões (USER/ADMIN)**.

---

## 💡 Funcionalidades principais

- 👤 **Cadastro e autenticação de usuários**
    - Registro e login com senhas criptografadas (BCrypt)
    - Geração de **token JWT** com tempo de expiração configurável
    - Perfis de acesso: `USER` e `ADMIN`

- 🎓 **Gestão de entidades**
    - CRUD completo de **Cursos**, **Faculdades** e **Técnicos**
    - Relacionamentos entre entidades (`Faculdade` ↔ `Curso` ↔ `Técnico`)

- 🔒 **Segurança avançada**
    - **Spring Security + JWT + Auth0**
    - Autenticação stateless (sem sessão)
    - Permissões definidas por rota (ex: `/auth/alterRole` apenas para ADMIN)

- 🧾 **Auditoria e monitoramento**
    - Registro automático de todos os acessos via `AccessLoggerFilter`
    - Armazena IP, endpoint, método HTTP, status e timestamp no banco

- 🐳 **Containerização com Docker**
    - Banco de dados **PostgreSQL** rodando via container
    - Configuração pronta em `docker-compose.yml`
    - Facilita o setup e garante portabilidade

---

## ⚙️ Tecnologias utilizadas

| Tecnologia | Descrição |
|-------------|------------|
| **Java 21 / OpenJDK 24** | Linguagem base do projeto |
| **Spring Boot 3.5.6** | Framework principal de desenvolvimento |
| **Spring Security** | Implementação da camada de segurança |
| **JWT (JSON Web Token)** | Autenticação e autorização seguras |
| **Auth0** | Integração externa de autenticação (opcional) |
| **Spring Data JPA / Hibernate** | ORM e persistência de dados |
| **PostgreSQL** | Banco de dados relacional |
| **Docker / Docker Compose** | Ambiente containerizado |
| **Lombok** | Redução de boilerplate no código |
| **Swagger / OpenAPI** | Documentação automática da API |

---

## 🧩 Estrutura do projeto

```
src/
 ├── main/
 │   ├── java/digital/rj/apicadastrodecursos/
 │   │   ├── Auth/                → Módulo de autenticação (User, Roles, JWT)
 │   │   ├── Cursos/              → Módulo de cursos
 │   │   ├── Faculdades/          → Módulo de faculdades
 │   │   ├── Tecnicos/            → Módulo de técnicos
 │   │   ├── infra/               → Exceções, configurações e utilitários
 │   │   └── ApiCadastroDeCursosApplication.java
 │   └── resources/
 │       ├── application.yml
 │       └── schema.sql (opcional)
 └── test/                        → Testes unitários e de integração
```

---

## 🔧 Configuração e execução local

### 🐋 Pré-requisitos

- **Java 21+**
- **Maven 3.9+**
- **Docker + Docker Compose**

### ⚙️ Configuração do ambiente

Crie um arquivo `.env` na raiz do projeto com suas variáveis:

```env
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin123
POSTGRES_DB=cursos_db
POSTGRES_PORT=5432
TOKEN_SECRET=sua_chave_secreta_aqui
```

### ▶️ Executando a aplicação

#### 1️⃣ Suba o banco de dados via Docker:
```bash
docker-compose up -d
```

#### 2️⃣ Rode a aplicação:
```bash
mvn spring-boot:run
```

A aplicação iniciará em:
👉 **http://localhost:8080**

#### 3️⃣ Acesse o Swagger:
👉 **http://localhost:8080/swagger-ui.html**

---

## 🔑 Autenticação

A autenticação é baseada em **JWT Token**.

Exemplo de fluxo:
1. Faça login em `/auth/login` enviando email e senha.
2. O retorno será um **token JWT**.
3. Envie esse token no header `Authorization` em todas as requisições:
   ```
   Authorization: Bearer seu_token_aqui
   ```

---

## 🔒 Controle de acesso

| Rota | Acesso | Descrição |
|------|---------|-----------|
| `POST /auth/register` | Público | Cria um novo usuário |
| `POST /auth/login` | Público | Realiza login e retorna token |
| `POST /auth/alterRole` | ADMIN | Altera permissões de um usuário |
| `GET /courses/**` | USER / ADMIN | Consulta cursos |
| `POST /college/**` | ADMIN | Gerencia faculdades |

---

## 🧾 Auditoria

Todas as requisições são registradas automaticamente na tabela `tb_access_logs` com os seguintes dados:

- IP do cliente
- Endpoint acessado
- Método HTTP
- Código de status
- Timestamp da requisição
- Resultado (sucesso/falha)

---

## 🧠 Objetivo

Este projeto foi desenvolvido com foco em:
- **Segurança e boas práticas REST**
- **Autenticação moderna com JWT**
- **Auditoria e rastreabilidade**
- **Escalabilidade e modularidade de código**
- **Ambiente padronizado com Docker**

---

## 🧑‍💻 Autor

**Pedro Peixoto**  
Desenvolvedor Backend Java ☕ | Spring Boot | APIs REST | Segurança | Docker

🔗 GitHub: [github.com/Peixotim](https://github.com/Peixotim)  
💼 LinkedIn: [linkedin.com/in/Peixotim](https://linkedin.com/in/Peixotim)

---

## 🪪 Licença

Este projeto está licenciado sob a licença **MIT** — sinta-se livre para utilizar, estudar e contribuir!

---

> “Segurança não é uma feature, é um princípio.” 💻🔒  
> — Projeto desenvolvido com dedicação, aprendizado e boas práticas.
