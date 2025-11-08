# Camada de Apresentação (Presentation Layer) - API ShortLink

## Visão Geral

Este documento descreve a implementação da camada de controllers (endpoints) da API ShortLink, desenvolvida como MVP para o projeto acadêmico. A camada de apresentação é responsável por receber requisições HTTP, validar dados de entrada através de DTOs e delegar a lógica de negócio para a camada de serviço.

## Estrutura de Pastas

```
library/src/main/java/br/com/senacsp/tads/stads4ma/library/
├── models/                          # DTOs (Data Transfer Objects)
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   ├── LinkRequest.java
│   ├── GroupRequest.java
│   ├── AddMemberRequest.java
│   └── AddLinkToGroupRequest.java
└── presentation/                    # Controllers (Endpoints)
    ├── AuthController.java
    ├── LinkController.java
    ├── GroupController.java
    ├── MovementHistoryController.java
    ├── ClickController.java
    ├── RedirectController.java
    └── UserController.java          # (já existente)
```

## DTOs (Data Transfer Objects)

### RegisterRequest
**Localização:** `models/RegisterRequest.java`

DTO para registro de novos usuários no sistema.

**Campos:**
- `name` (String, obrigatório): Nome do usuário
- `email` (String, obrigatório): Email válido do usuário
- `password` (String, obrigatório): Senha do usuário
- `planId` (Long, obrigatório): ID do plano de assinatura

**Validações:**
- Nome não pode estar em branco
- Email deve ser válido e não pode estar em branco
- Senha não pode estar em branco
- ID do plano é obrigatório

---

### LoginRequest
**Localização:** `models/LoginRequest.java`

DTO para autenticação de usuários.

**Campos:**
- `email` (String, obrigatório): Email do usuário
- `password` (String, obrigatório): Senha do usuário

**Validações:**
- Email deve ser válido e não pode estar em branco
- Senha não pode estar em branco

---

### LinkRequest
**Localização:** `models/LinkRequest.java`

DTO para criação e atualização de links encurtados.

**Campos:**
- `originalUrl` (String, obrigatório): URL original a ser encurtada
- `groupId` (UUID, opcional): ID do grupo ao qual o link pertence
- `isActive` (Boolean, opcional): Status ativo/inativo do link
- `expiresAt` (String, opcional): Data de expiração do link

**Validações:**
- URL original não pode estar em branco

---

### GroupRequest
**Localização:** `models/GroupRequest.java`

DTO para criação de grupos.

**Campos:**
- `name` (String, obrigatório): Nome do grupo

**Validações:**
- Nome não pode estar em branco

---

### AddMemberRequest
**Localização:** `models/AddMemberRequest.java`

DTO para adicionar membros a um grupo.

**Campos:**
- `userId` (UUID, obrigatório): ID do usuário a ser adicionado

**Validações:**
- ID do usuário é obrigatório

---

### AddLinkToGroupRequest
**Localização:** `models/AddLinkToGroupRequest.java`

DTO para adicionar um link a um grupo.

**Campos:**
- `groupId` (UUID, obrigatório): ID do grupo destino

**Validações:**
- ID do grupo é obrigatório

---

## Controllers

### 1. AuthController
**Localização:** `presentation/AuthController.java`  
**Base URL:** `/auth`

Controlador responsável pela autenticação de usuários.

#### Endpoints:

##### POST `/auth/register`
Registra um novo usuário no sistema.

**Request Body:** `RegisterRequest`
```json
{
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "senha123",
  "planId": 1
}
```

**Response:** `201 Created`
```json
{
  "message": "Usuário registrado com sucesso!"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### POST `/auth/login`
Realiza login e retorna token JWT.

**Request Body:** `LoginRequest`
```json
{
  "email": "joao@example.com",
  "password": "senha123"
}
```

**Response:** `200 OK`
```json
{
  "message": "Login realizado com sucesso! Token: [JWT_TOKEN]"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

### 2. LinkController
**Localização:** `presentation/LinkController.java`  
**Base URL:** `/links`

Controlador responsável pelos CRUDs de Links. Cada link pertence a um criador (user_id) e pode ou não pertencer a um grupo (group_id opcional).

#### Endpoints:

##### GET `/links`
Lista todos os links do usuário autenticado.

**Query Parameters:**
- `groupId` (UUID, opcional): Filtra links de um grupo específico
- `active` (Boolean, opcional): Filtra por status ativo/inativo

**Exemplo:** `GET /links?groupId=123e4567-e89b-12d3-a456-426614174000&active=true`

**Response:** `200 OK`
```json
{
  "message": "Lista de links do usuário"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### POST `/links`
Cria um novo link (respeitando limite do plano).

**Request Body:** `LinkRequest`
```json
{
  "originalUrl": "https://example.com/very/long/url",
  "groupId": "123e4567-e89b-12d3-a456-426614174000",
  "isActive": true,
  "expiresAt": "2024-12-31"
}
```

**Response:** `201 Created`
```json
{
  "message": "Link criado com sucesso"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### PATCH `/links/{id}`
Atualiza um link existente do usuário autenticado.

**Path Parameter:**
- `id` (UUID): ID do link a ser atualizado

**Request Body:** `LinkRequest` (campos opcionais)

**Response:** `200 OK`
```json
{
  "message": "Link atualizado com sucesso"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### DELETE `/links/{id}`
Deleta um link criado pelo usuário autenticado.

**Path Parameter:**
- `id` (UUID): ID do link

**Response:** `204 No Content`

**Status:** Implementado (lógica de negócio pendente)

---

##### POST `/links/{id}/add-to-group`
Adiciona um link existente a um grupo.

**Path Parameter:**
- `id` (UUID): ID do link

**Request Body:** `AddLinkToGroupRequest`
```json
{
  "groupId": "123e4567-e89b-12d3-a456-426614174000"
}
```

**Response:** `200 OK`
```json
{
  "message": "Link adicionado ao grupo"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### DELETE `/links/{id}/remove-from-group`
Remove um link de um grupo.

**Path Parameter:**
- `id` (UUID): ID do link

**Response:** `200 OK`
```json
{
  "message": "Link removido do grupo"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

### 3. GroupController
**Localização:** `presentation/GroupController.java`  
**Base URL:** `/groups`

Controlador responsável pela gestão de grupos, incluindo criação, listagem, adição e remoção de membros, e visualização de links do grupo.

#### Endpoints:

##### GET `/groups`
Lista todos os grupos que o usuário autenticado participa.

**Response:** `200 OK`
```json
{
  "message": "Lista de grupos do usuário"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### POST `/groups`
Cria um novo grupo.

**Request Body:** `GroupRequest`
```json
{
  "name": "Grupo de Marketing"
}
```

**Response:** `201 Created`
```json
{
  "message": "Grupo criado"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### POST `/groups/{groupId}/members`
Adiciona um membro ao grupo.

**Path Parameter:**
- `groupId` (UUID): ID do grupo

**Request Body:** `AddMemberRequest`
```json
{
  "userId": "123e4567-e89b-12d3-a456-426614174000"
}
```

**Response:** `200 OK`
```json
{
  "message": "Membro adicionado"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### DELETE `/groups/{groupId}/members/{userId}`
Remove um membro do grupo.

**Path Parameters:**
- `groupId` (UUID): ID do grupo
- `userId` (UUID): ID do usuário a remover

**Response:** `200 OK`
```json
{
  "message": "Membro removido"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

##### GET `/groups/{groupId}/links`
Lista os links pertencentes a um grupo.

**Path Parameter:**
- `groupId` (UUID): ID do grupo

**Response:** `200 OK`
```json
{
  "message": "Links do grupo"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

### 4. MovementHistoryController
**Localização:** `presentation/MovementHistoryController.java`  
**Base URL:** `/history`

Controlador para histórico de movimentações do usuário. Registra e lista ações CRUD (link criado, atualizado, etc.).

#### Endpoints:

##### GET `/history`
Lista o histórico de ações do usuário autenticado.

**Query Parameters:**
- `entity` (String, opcional): Filtra por tipo (link, group, user)

**Exemplo:** `GET /history?entity=link`

**Response:** `200 OK`
```json
{
  "message": "Histórico de movimentações"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

### 5. ClickController
**Localização:** `presentation/ClickController.java`  
**Base URL:** `/links`

Controlador para registro e consulta de cliques nos links.

#### Endpoints:

##### GET `/links/{linkId}/clicks`
Lista todos os cliques de um link (visível para criador ou membros do grupo).

**Path Parameter:**
- `linkId` (UUID): ID do link

**Response:** `200 OK`
```json
{
  "message": "Lista de cliques do link"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

### 6. RedirectController
**Localização:** `presentation/RedirectController.java`  
**Base URL:** `/`

Controlador para redirecionamento de links encurtados. Registra click no link e redireciona o usuário para a URL original.

#### Endpoints:

##### GET `/{shortCode}`
Registra click no link e redireciona o usuário.

**Path Parameter:**
- `shortCode` (String): Código curto do link

**Exemplo:** `GET /abc123`

**Response:** `200 OK` (deve retornar redirecionamento HTTP 302)
```json
{
  "message": "redirect de usuario e registro de click"
}
```

**Status:** Implementado (lógica de negócio pendente)

---

## Resumo de Endpoints

### Autenticação
- `POST /auth/register` - Registro de usuário
- `POST /auth/login` - Login de usuário

### Links
- `GET /links` - Listar links do usuário
- `POST /links` - Criar novo link
- `PATCH /links/{id}` - Atualizar link
- `DELETE /links/{id}` - Deletar link
- `POST /links/{id}/add-to-group` - Adicionar link a grupo
- `DELETE /links/{id}/remove-from-group` - Remover link de grupo
- `GET /links/{linkId}/clicks` - Listar cliques de um link

### Grupos
- `GET /groups` - Listar grupos do usuário
- `POST /groups` - Criar novo grupo
- `POST /groups/{groupId}/members` - Adicionar membro ao grupo
- `DELETE /groups/{groupId}/members/{userId}` - Remover membro do grupo
- `GET /groups/{groupId}/links` - Listar links do grupo

### Histórico
- `GET /history` - Listar histórico de movimentações

### Redirecionamento
- `GET /{shortCode}` - Redirecionar link encurtado

---

## Observações Importantes

### Status de Implementação
Todos os controllers foram implementados com a estrutura básica e validações de entrada através de DTOs. No entanto, a lógica de negócio está marcada com `TODO` e precisa ser implementada na camada de serviço.

### Próximos Passos
1. Implementar serviços correspondentes para cada controller
2. Implementar autenticação JWT
3. Implementar validação de permissões (usuário autenticado, membro do grupo, etc.)
4. Implementar geração de shortCode único
5. Implementar validação de limites do plano
6. Implementar registro de cliques e histórico de movimentações
7. Implementar redirecionamento HTTP 302

### Validações
Todos os DTOs utilizam anotações do Jakarta Validation (`@NotBlank`, `@NotNull`, `@Email`) para validação de entrada. É necessário configurar o tratamento de exceções de validação no projeto.

### Convenções
- Todos os controllers seguem o padrão REST
- Utilizam `ResponseEntity` para respostas HTTP
- Códigos de status HTTP apropriados (200, 201, 204, etc.)
- Documentação JavaDoc em todos os endpoints

---

## Estrutura de Dependências

Os controllers dependem de:
- Spring Web (`@RestController`, `@RequestMapping`, etc.)
- Jakarta Validation (validações nos DTOs)
- Lombok (para records nos DTOs)

---

**Data de Criação:** 2024  
**Versão:** MVP 1.0  
**Autor:** Equipe ShortLink DSW

