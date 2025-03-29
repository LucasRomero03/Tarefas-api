# TODO List API with Spring Boot

API para gerenciamento de tarefas (CRUD) com autenticação JWT e suporte a múltiplos bancos de dados (H2 e PostgreSQL).

## Tecnologias Utilizadas

- **Spring Boot** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - Construção de API REST
- **Spring Security** - Autenticação e autorização
- **Spring Validation** - Validação de dados
- **Spring WebFlux** - Suporte a programação reativa
- **Spring Boot Admin Client** - Monitoramento da aplicação
- **Spring Actuator** - Métricas e health checks
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **PostgreSQL** - Banco de dados relacional para produção
- **Lombok** - Redução de boilerplate code
- **Java JWT (Auth0)** - Implementação de JWT para autenticação
- **Spring Boot DevTools** - Ferramentas de desenvolvimento

## Principais Funcionalidades

- CRUD completo de tarefas
- Autenticação via JWT
- Paginação de resultados
- Validação de dados de entrada
- Monitoramento via Spring Boot Admin
- Suporte a múltiplos bancos de dados (H2 e PostgreSQL)
- API documentada seguindo padrões REST

## Como Executar o Projeto

1. **Pré-requisitos:**
   - Java 17+
   - Maven
   - PostgreSQL (opcional, pode usar H2 em memória)

2. **Configuração:**
   - Clone o repositório:
     ```bash
     git clone <URL_DO_REPOSITORIO>
     cd nome-do-projeto
     ```
   - Configure o banco de dados no `application.properties` (ou use H2 padrão)

3. **Build e execução:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acesse a aplicação:**
   - API: `http://localhost:8080/todos`
   - Documentação: `http://localhost:8080/swagger-ui.html` (se configurado)
   - Spring Boot Admin: `http://localhost:8080/actuator` (requer configuração do servidor Admin)

## Endpoints da API

### Autenticação
```http
POST /auth/login
```
#### Exemplo de corpo:
```json
{
  "username": "user",
  "password": "password"
}
```

### Tarefas (Todos)

#### Criar Tarefa (autenticação requerida)
```http
POST /todos
```
#### Exemplo de corpo:
```json
{
  "nome": "Minha Tarefa",
  "descricao": "Descrição detalhada",
  "prioridade": 1
}
```

#### Listar Tarefas (com paginação)
```http
GET /todos?page=0&size=10&sort=nome,asc
```

#### Buscar Tarefa por ID
```http
GET /todos/{id}
```

#### Buscar Tarefas por Nome
```http
GET /todos/nome?nome=Minha
```

#### Atualizar Tarefa
```http
PUT /todos/{id}
```

#### Excluir Tarefa
```http
DELETE /todos/{id}
```

## Configuração de Ambiente

### Banco de Dados
- **H2** (padrão para desenvolvimento): Acessível em `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Usuário: `sa`
  - Senha: (vazio)

- **PostgreSQL**: Configure no `application.properties` para ambiente de produção

### Segurança
- A API utiliza JWT para autenticação
- Configure o secret JWT no `application.properties`


