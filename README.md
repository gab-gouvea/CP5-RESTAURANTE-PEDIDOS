# 🍽️ Sistema de Pedidos para Restaurante

Sistema completo de gerenciamento de pedidos para restaurantes desenvolvido com Spring Boot, oferecendo APIs REST para gestão de cardápio e pedidos.

## 📋 Índice

- [Características](#-características)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Uso](#-uso)
- [API Endpoints](#-api-endpoints)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Modelos de Dados](#-modelos-de-dados)
- [Exemplos de Requisições](#-exemplos-de-requisições)
- [Contribuição](#-contribuição)

## 🚀 Características

- ✅ **Gestão de Cardápio**: CRUD completo para itens do menu
- ✅ **Gestão de Pedidos**: Criação, atualização e acompanhamento de pedidos
- ✅ **Categorização**: Organização por categorias (Pizza, Lanche, Bebida, etc.)
- ✅ **Status de Pedidos**: Controle completo do fluxo (Pendente → Confirmado → Preparando → Pronto → Entregue)
- ✅ **Tipos de Pedido**: Suporte para Delivery, Balcão e Mesa
- ✅ **Validações**: Validação robusta de dados de entrada
- ✅ **Tratamento de Erros**: Sistema de exceções personalizado
- ✅ **Auditoria**: Timestamps automáticos para criação e atualização

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Spring Web**
- **Spring Validation**
- **MySQL 8.0**
- **Lombok**
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Instalação

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd pedidos
   ```

2. **Configure o banco de dados MySQL**
   ```sql
   CREATE DATABASE restaurante_pedidos;
   ```

3. **Configure as credenciais do banco** (veja seção [Configuração](#-configuração))

4. **Execute o projeto**
   ```bash
   mvn spring-boot:run
   ```

   Ou compile e execute:
   ```bash
   mvn clean install
   java -jar target/pedidos-0.0.1-SNAPSHOT.jar
   ```

## ⚙️ Configuração

### Banco de Dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/restaurante_pedidos?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Porta do servidor
server.port=8080
```

### Variáveis de Ambiente (Produção)

Para produção, use variáveis de ambiente:

```bash
export DB_URL=jdbc:mysql://localhost:3306/restaurante_pedidos
export DB_USERNAME=seu_usuario
export DB_PASSWORD=sua_senha
```

## 🎯 Uso

Após iniciar a aplicação, a API estará disponível em:
- **Base URL**: `http://localhost:8080`
- **Health Check**: `http://localhost:8080/actuator/health` (se habilitado)

## 📡 API Endpoints

### 🍕 Itens do Cardápio (`/api/itens`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/itens` | Lista todos os itens |
| `GET` | `/api/itens/ativos` | Lista apenas itens ativos |
| `GET` | `/api/itens/categoria/{categoria}` | Lista itens por categoria |
| `GET` | `/api/itens/{id}` | Busca item por ID |
| `POST` | `/api/itens` | Cria novo item |
| `PUT` | `/api/itens/{id}` | Atualiza item |
| `PATCH` | `/api/itens/{id}/ativar` | Ativa item |
| `PATCH` | `/api/itens/{id}/inativar` | Inativa item |
| `DELETE` | `/api/itens/{id}` | Remove item |

### 📋 Pedidos (`/api/pedidos`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/pedidos` | Lista todos os pedidos |
| `GET` | `/api/pedidos/{id}` | Busca pedido por ID |
| `GET` | `/api/pedidos/status/{status}` | Lista pedidos por status |
| `GET` | `/api/pedidos/tipo/{tipo}` | Lista pedidos por tipo |
| `POST` | `/api/pedidos` | Cria novo pedido |
| `PATCH` | `/api/pedidos/{id}/status` | Atualiza status |
| `PATCH` | `/api/pedidos/{id}/confirmar` | Confirma pedido |
| `PATCH` | `/api/pedidos/{id}/cancelar` | Cancela pedido |

## 🏗️ Estrutura do Projeto

```
src/main/java/br/com/restaurante/pedidos/
├── PedidosApplication.java          # Classe principal
├── controller/                      # Controllers REST
│   ├── ItemController.java
│   └── PedidoController.java
├── dto/                            # Data Transfer Objects
│   ├── ItemRequestDTO.java
│   ├── ItemResponseDTO.java
│   ├── ItemPedidoRequestDTO.java
│   ├── ItemPedidoResponseDTO.java
│   ├── PedidoRequestDTO.java
│   ├── PedidoResponseDTO.java
│   └── StatusUpdateDTO.java
├── entity/                         # Entidades JPA
│   ├── Item.java
│   ├── ItemPedido.java
│   └── Pedido.java
├── exception/                      # Tratamento de exceções
│   └── GlobalExceptionHandler.java
├── repository/                     # Repositórios JPA
│   ├── ItemRepository.java
│   ├── ItemPedidoRepository.java
│   └── PedidoRepository.java
└── service/                       # Lógica de negócio
    ├── ItemService.java
    └── PedidoService.java
```

## 📊 Modelos de Dados

### Item (Cardápio)
```java
{
  "id": 1,
  "nome": "Pizza Margherita",
  "descricao": "Pizza com molho de tomate, mussarela e manjericão",
  "preco": 35.90,
  "categoria": "PIZZA",
  "ativo": true,
  "dataCriacao": "2024-01-15T10:30:00",
  "dataAtualizacao": "2024-01-15T10:30:00"
}
```

**Categorias disponíveis**: `ENTRADA`, `PRATO_PRINCIPAL`, `ACOMPANHAMENTO`, `SOBREMESA`, `BEBIDA`, `LANCHE`, `PIZZA`, `SALADA`

### Pedido
```java
{
  "id": 1,
  "nomeCliente": "João Silva",
  "telefoneCliente": "11999999999",
  "enderecoEntrega": "Rua das Flores, 123",
  "tipoPedido": "DELIVERY",
  "status": "PENDENTE",
  "valorTotal": 71.80,
  "observacoes": "Sem cebola",
  "dataEntregaPrevista": "2024-01-15T12:30:00",
  "dataCriacao": "2024-01-15T11:00:00",
  "itens": [...]
}
```

**Status disponíveis**: `PENDENTE`, `CONFIRMADO`, `PREPARANDO`, `PRONTO`, `ENTREGUE`, `CANCELADO`

**Tipos disponíveis**: `DELIVERY`, `BALCAO`, `MESA`

## 🔧 Exemplos de Requisições

### Criar Item do Cardápio

```bash
curl -X POST http://localhost:8080/api/itens \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Hambúrguer Clássico",
    "descricao": "Hambúrguer com carne, queijo, alface e tomate",
    "preco": 25.50,
    "categoria": "LANCHE",
    "ativo": true
  }'
```

### Criar Pedido

```bash
curl -X POST http://localhost:8080/api/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "nomeCliente": "Maria Santos",
    "telefoneCliente": "11888888888",
    "enderecoEntrega": "Av. Paulista, 1000",
    "tipoPedido": "DELIVERY",
    "observacoes": "Entregar no portão",
    "dataEntregaPrevista": "2024-01-15T12:30:00",
    "itens": [
      {
        "itemId": 1,
        "quantidade": 2,
        "observacoesItem": "Sem picles"
      }
    ]
  }'
```

### Listar Itens por Categoria

```bash
curl http://localhost:8080/api/itens/categoria/PIZZA
```

### Atualizar Status do Pedido

```bash
curl -X PATCH http://localhost:8080/api/pedidos/1/status \
  -H "Content-Type: application/json" \
  -d '{
    "status": "CONFIRMADO"
  }'
```

### Listar Pedidos por Status

```bash
curl http://localhost:8080/api/pedidos/status/PREPARANDO
```

## 🔍 Validações

O sistema inclui validações robustas:

- **Nome do cliente**: 2-100 caracteres, obrigatório
- **Telefone**: Formato brasileiro, obrigatório
- **Endereço**: Máximo 200 caracteres
- **Observações**: Máximo 500 caracteres
- **Preço**: Valores positivos com 2 casas decimais
- **Quantidade**: Números inteiros positivos

## 🚨 Tratamento de Erros

A API retorna respostas padronizadas para erros:

```json
{
  "timestamp": "2024-01-15T11:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Nome do cliente é obrigatório",
  "path": "/api/pedidos"
}
```

## 🧪 Testes

Execute os testes com:

```bash
mvn test
```

## 📝 Logs

Os logs da aplicação incluem:
- Consultas SQL (em desenvolvimento)
- Erros e exceções
- Operações de CRUD
- Validações de entrada

