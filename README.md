

<h1 align="center">📦 CadastroSpringBoot</h1>

<p align="center">
  API RESTful construída com Java e Spring Boot, com persistência em banco de dados MySQL.<br>
  Projeto pessoal com foco em operações CRUD, boas práticas e arquitetura simples.
</p>

---

## 🚀 Sobre o projeto

Este projeto simula um sistema de cadastro de "Ninjas" e suas "Missões", com funcionalidades completas de criação, leitura, atualização e remoção (CRUD).

Apesar dos nomes descontraídos, o foco está em praticar conceitos reais do backend com Spring Boot, banco de dados relacional, mapeamento com DTOs e camadas bem definidas.

---

## 🛠️ Tecnologias e ferramentas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman (para testes)
- Git & GitHub

---

## 🧱 Arquitetura do projeto

- `model`: representa as entidades do banco (Ninja, Missao)
- `repository`: interfaces JPA para acesso ao banco
- `service`: regra de negócio da aplicação
- `controller`: endpoints expostos via REST
- `dto`: objetos de transferência de dados
- `mapper`: conversão entre Model <-> DTO

---

## 🔧 Como executar o projeto

### Pré-requisitos:
- Java 17+
- MySQL instalado e rodando
- Maven

### Passos:

1. Clone o repositório:
```bash
git clone https://github.com/MatheusFreiresDev/CadastroSpringBoot.git
````

2. Configure o banco no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ninjadb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. Execute o projeto:

```bash
./mvnw spring-boot:run
```

4. Teste os endpoints via Postman ou Insomnia.

---

## 📌 Funcionalidades

* ✅ Cadastro de ninjas
* ✅ Cadastro de missões
* ✅ Relacionamento entre ninja e missões
* ✅ Atualizar, buscar e deletar entidades
* ✅ Respostas customizadas com `ResponseEntity`

---

## 📷 Exemplos de requisições (em breve)

*📌 Em breve prints do Postman com exemplos de requisições e respostas*

---

## 📫 Contato

Feito com 💻 por **Matheus Freires**

* [LinkedIn](https://www.linkedin.com/in/matheusfreiresdev/)
* [GitHub](https://github.com/MatheusFreiresDev)

---

<p align="center">
  "O caminho é longo, mas o objetivo é claro." 🚀
</p>
```

---

