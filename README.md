# 🏗️ Hexa-Core API - Arquitetura Hexagonal

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-20+-blue?style=for-the-badge&logo=docker)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=for-the-badge&logo=apache-maven)

**API REST demonstrando implementação de Arquitetura Hexagonal (Ports & Adapters) com Spring Boot**

[Instalação](#-instalação) • [Uso](#-como-usar) • [API](#-endpoints) • [Arquitetura](#️-arquitetura) • [Contribuição](#-contribuição)

</div>

---

## 📋 Sobre o Projeto

O **Hexa-Core** é uma aplicação de demonstração que implementa os princípios da **Arquitetura Hexagonal** (também conhecida como Ports & Adapters) para gerenciamento de **Clientes** e **Pedidos**. 

### 🎯 Objetivos

- ✅ Demonstrar implementação prática de **Arquitetura Hexagonal**
- ✅ Aplicar princípios **SOLID** em código real
- ✅ Separação clara de responsabilidades entre camadas
- ✅ API REST completamente funcional com **CRUD completo**
- ✅ Regras de negócio isoladas e testáveis
- ✅ Integração com PostgreSQL via Docker

### 🚀 Funcionalidades

- **Gestão de Clientes**: CRUD completo com validações
- **Gestão de Pedidos**: CRUD com regras de negócio automáticas
- **Desconto Automático**: 10% para pedidos ≥ R$ 100,00
- **Validações**: Email, campos obrigatórios, valores positivos
- **Documentação**: Swagger/OpenAPI integrado
- **Banco de Dados**: PostgreSQL com Docker Compose
- **Profiles**: Desenvolvimento (H2) e Produção (PostgreSQL)

---

## 🏛️ Arquitetura

### Arquitetura Hexagonal (Ports & Adapters)

```
🌐 Adapters Inbound          🎯 Core Domain              🔌 Adapters Outbound
┌─────────────────────┐      ┌─────────────────────┐      ┌─────────────────────┐
│   Controllers       │────▶ │                     │ ◀────│   Repository        │
│   - ClienteREST     │      │   Domain Objects    │      │   Adapters          │
│   - PedidoREST      │      │   - Cliente         │      │   - ClienteRepo     │
│                     │      │   - Pedido          │      │   - PedidoRepo      │
├─────────────────────┤      │                     │      ├─────────────────────┤
│   DTOs & Mappers    │      │   Use Cases         │      │   JPA Entities      │
│   - ClienteDTO      │      │   - CreatePedido    │      │   - ClienteEntity   │
│   - PedidoDTO       │      │   - UpdateCliente   │      │   - PedidoEntity    │
│                     │      │                     │      │                     │
├─────────────────────┤      │   Business Rules    │      ├─────────────────────┤
│   Services Layer    │      │   - Validations     │      │   Database          │
│   - ClienteService  │      │   - Discount Logic  │      │   - PostgreSQL      │
│   - PedidoService   │      │   - Status Control  │      │   - H2 (dev)        │
└─────────────────────┘      └─────────────────────┘      └─────────────────────┘
```

### 📦 Estrutura de Pacotes

```
src/main/java/arqui/hexa_core/
├── 🎯 core/                    # Núcleo da aplicação (sem dependências externas)
│   ├── domain/                 # Objetos de domínio (Cliente, Pedido)
│   └── ports/                  # Contratos/Interfaces
│       ├── inbound/           # Portas de entrada (Use Cases)
│       └── outbound/          # Portas de saída (Repository)
├── 🏛️ application/             # Casos de uso (regras de negócio)
│   └── usecases/              # Implementação das regras
├── 🔌 adapters/               # Adaptadores (implementações das portas)
│   ├── inbound/               # Adaptadores de entrada
│   │   ├── controllers/       # Controllers REST
│   │   ├── dtos/             # Data Transfer Objects
│   │   └── mappers/          # Conversores DTO ↔ Domain
│   └── outbound/             # Adaptadores de saída
│       ├── adapters/         # Implementações Repository
│       ├── entities/         # Entidades JPA
│       └── repositories/     # Interfaces JPA Repository
├── 🔧 services/               # Camada de serviços (orquestração)
└── ⚙️ config/                # Configurações (Swagger, etc.)
```

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.2.1** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validações automáticas
- **Hibernate** - ORM

### Banco de Dados
- **PostgreSQL 15** - Banco principal (produção)
- **H2 Database** - Banco em memória (desenvolvimento)

### Documentação
- **SpringDoc OpenAPI** - Geração automática do Swagger
- **Swagger UI** - Interface interativa da API

### DevOps & Ferramentas
- **Docker & Docker Compose** - Containerização
- **Maven** - Gerenciador de dependências
- **PgAdmin** - Administração do PostgreSQL

---

## 📦 Instalação

### Pré-requisitos

- **Java 21+** instalado
- **Maven 3.6+** instalado
- **Docker & Docker Compose** instalado
- **Git** instalado

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/hexa-core.git
cd hexa-core
```

### 2️⃣ Subir o Banco PostgreSQL

```bash
# Iniciar apenas o banco de dados
docker-compose up -d db

# Opcional: Subir PgAdmin também
docker-compose up -d db pgadmin
```

### 3️⃣ Executar a Aplicação

```bash
# Compilar e executar
./mvnw spring-boot:run

# Ou com profile específico
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

### 4️⃣ Verificar se está funcionando

- **API Swagger**: http://localhost:8080/swagger-ui.html
- **PgAdmin**: http://localhost:5050 (admin@admin.com / admin)

---

## 🚀 Como Usar

### Profiles Disponíveis

| Profile | Banco | Uso | Comando |
|---------|-------|-----|---------|
| `dev` | H2 (memória) | Desenvolvimento | `--spring.profiles.active=dev` |
| `prod` | PostgreSQL | Produção/Docker | `--spring.profiles.active=prod` |

### Configuração do Banco PostgreSQL

```yaml
# docker-compose.yml
Database: hexa_db
Usuário: hexa_user  
Senha: hexa_pass
Porta: 5432
```

### Acessos Importantes

| Serviço | URL | Credenciais |
|---------|-----|-------------|
| **API Swagger** | http://localhost:8080/swagger-ui.html | - |
| **PgAdmin** | http://localhost:5050 | admin@admin.com / admin |
| **H2 Console** | http://localhost:8080/h2-console | sa / password |

---

## 📚 API Endpoints

### 👥 Clientes (`/api/clientes`)

| Método | Endpoint | Descrição | Corpo |
|--------|----------|-----------|-------|
| `POST` | `/api/clientes` | Criar cliente | `{"nome":"João","email":"joao@email.com","telefone":"11999999999"}` |
| `GET` | `/api/clientes` | Listar todos | - |
| `GET` | `/api/clientes/{id}` | Buscar por ID | - |
| `PUT` | `/api/clientes/{id}` | Atualizar | `{"nome":"João Silva","email":"joao@email.com","telefone":"11888888888"}` |
| `DELETE` | `/api/clientes/{id}` | Deletar | - |

### 🛒 Pedidos (`/api/pedidos`)

| Método | Endpoint | Descrição | Corpo |
|--------|----------|-----------|-------|
| `POST` | `/api/pedidos` | Criar pedido | `{"clienteId":1,"valor":150.00}` |
| `GET` | `/api/pedidos` | Listar todos | - |
| `GET` | `/api/pedidos/{id}` | Buscar por ID | - |
| `PUT` | `/api/pedidos/{id}` | Atualizar | `{"clienteId":1,"valor":200.00}` |
| `DELETE` | `/api/pedidos/{id}` | Deletar | - |

### 💡 Regras de Negócio Automáticas

#### Desconto em Pedidos
- **Valor ≥ R$ 100,00**: Desconto automático de **10%**
- **Valor < R$ 100,00**: Sem desconto

#### Exemplo Prático
```json
// REQUEST
{
  "clienteId": 1,
  "valor": 150.00
}

// RESPONSE (desconto aplicado)
{
  "id": 1,
  "clienteId": 1,
  "valor": 135.00  // 150 - 15 (10% desconto)
}
```

---

## 🧪 Testando a API

### Via Swagger UI
1. Acesse http://localhost:8080/swagger-ui.html
2. Teste todos os endpoints diretamente na interface
3. Veja exemplos de request/response

### Via cURL

```bash
# Criar Cliente
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","email":"joao@email.com","telefone":"11999999999"}'

# Criar Pedido (com desconto)
curl -X POST http://localhost:8080/api/pedidos \
  -H "Content-Type: application/json" \
  -d '{"clienteId":1,"valor":150.00}'

# Listar Clientes
curl http://localhost:8080/api/clientes
```

---

## 🎯 Princípios SOLID Aplicados

### Single Responsibility Principle (SRP)
- **Controllers**: Apenas HTTP handling
- **Use Cases**: Apenas regras de negócio
- **Repositories**: Apenas persistência

### Open/Closed Principle (OCP)
- Novos use cases podem ser adicionados sem modificar código existente
- Interfaces permitem extensão sem modificação

### Liskov Substitution Principle (LSP)
- Qualquer implementação de `ClienteRepositoryPort` pode ser substituída
- Polimorfismo correto entre abstrações

### Interface Segregation Principle (ISP)
- Interfaces pequenas e específicas (`ClienteServicePort`, `PedidoRepositoryPort`)
- Clientes dependem apenas do que usam

### Dependency Inversion Principle (DIP)
- **Use Cases** dependem de **abstrações** (ports), não de implementações
- **Injeção de dependência** via Spring Boot

---

## 🔧 Configurações Avançadas

### Variáveis de Ambiente

```bash
# Banco de dados
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/hexa_db
SPRING_DATASOURCE_USERNAME=hexa_user
SPRING_DATASOURCE_PASSWORD=hexa_pass

# JPA
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
```

### Docker Compose Completo

```bash
# Subir tudo (app + banco + pgadmin)
docker-compose up -d

# Subir apenas serviços específicos
docker-compose up -d db pgadmin

# Ver logs
docker-compose logs -f

# Parar tudo
docker-compose down
```

---

## 📊 Monitoramento

### Logs da Aplicação
```bash
# Ver logs SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Ver logs específicos
logging.level.arqui.hexa_core=DEBUG
```

### Health Checks
- **Aplicação**: `GET /actuator/health`
- **Database**: Automático via Spring Boot Actuator

---

## 🧪 Testes

### Estrutura de Testes
```
src/test/java/
└── arqui/hexa_core/
    ├── unit/              # Testes unitários
    ├── integration/       # Testes de integração
    └── architecture/      # Testes arquiteturais
```

### Executar Testes

```bash
# Todos os testes
./mvnw test

# Apenas testes unitários
./mvnw test -Dtest="**/*UnitTest"

# Com coverage
./mvnw test jacoco:report
```

---

## 🚀 Deploy

### Build para Produção

```bash
# Gerar JAR
./mvnw clean package -DskipTests

# Executar JAR
java -jar target/hexa-core-0.0.1-SNAPSHOT.jar
```

### Docker Build

```bash
# Build da imagem
docker build -t hexa-core:latest .

# Executar container
docker run -p 8080:8080 hexa-core:latest
```

---

## 🤝 Contribuição

### Como Contribuir

1. **Fork** o repositório
2. Crie uma **branch** para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. Abra um **Pull Request**

### Padrões de Commit

```bash
feat: adicionar nova funcionalidade
fix: corrigir bug
docs: atualizar documentação
style: formatação de código
refactor: refatoração sem mudança de comportamento
test: adicionar/corrigir testes
chore: tarefas de manutenção
```

### Code Style

- **Checkstyle**: Seguir padrões Java
- **SonarQube**: Qualidade de código
- **SpotBugs**: Detecção de bugs

---

### 👨🏻‍💻 Autor:
<table style="border=0">
  <tr>
    <td align="left">
      <a href="https://github.com/ItaloRochaj">
        <span><b>Italo Rocha</b></span>
      </a>
      <br>
      <span>Full-Stack Development</span>
    </td>
  </tr>
</table>
