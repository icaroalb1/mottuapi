# Mottu API - Sistema de Gerenciamento de Motos

Sistema web completo desenvolvido com Spring Boot para gerenciamento de motos, vagas e movimentações, desenvolvido para o Challenge Mottu 2025.

## Integrantes
RM556270 - Bianca Vitoria - 2TDSPZ
RM555166 - Guilherme Camargo - 2TDSPM
RM555131 - Icaro Americo - 2TDSPM


## 🚀 Tecnologias Utilizadas

- **Spring Boot 3.4.5** - Framework principal
- **Java 21** - Linguagem de programação
- **Thymeleaf** - Engine de templates para frontend
- **Spring Security** - Autenticação e autorização
- **Flyway** - Controle de versões do banco de dados
- **PostgreSQL** - Banco de dados principal
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização (opcional)

## 📋 Funcionalidades

### 🔐 Sistema de Autenticação
- Login/logout com formulário
- Dois tipos de usuário:
  - **ADMIN**: Acesso completo ao sistema
  - **OPERADOR**: Acesso limitado às movimentações

### 🏍️ Gerenciamento de Motos
- Listar, criar, editar e excluir motos
- Controle de status (DISPONIVEL, EM_MANUTENCAO, etc.)
- Validações de dados obrigatórios

### 🅿️ Sistema de Vagas
- Gerenciamento de vagas de estacionamento
- Controle de ocupação
- Associação com motos

### 📊 Movimentações
- Sistema de check-in e check-out
- Histórico de movimentações
- Controle de usuário responsável

### 📍 Localização
- Sistema de rastreamento de motos
- Simulador de sensores
- API para recebimento de coordenadas

## 🛠️ Pré-requisitos

- Java 21 ou superior
- Maven 3.6 ou superior
- PostgreSQL 12 ou superior
- Git

## 📦 Instalação

### 1. Clone o repositório
```bash
git clone <URL_DO_REPOSITORIO>
cd mottuapi-1
```

### 2. Configure o banco de dados
```sql
-- Crie o banco de dados
CREATE DATABASE mottu;
CREATE USER mottu_user WITH PASSWORD 'mottu_password';
GRANT ALL PRIVILEGES ON DATABASE mottu TO mottu_user;
```

### 3. Configure as variáveis de ambiente
Edite o arquivo `src/main/resources/application.properties` se necessário:

```properties
# Datasource (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/mottu
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 4. Execute a aplicação
```bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn spring-boot:run
```

## 🐳 Execução com Docker (Opcional)

### 1. Build da imagem
```bash
docker build -t mottu-api .
```

### 2. Executar container
```bash
docker run -p 8081:8081 mottu-api
```

## 🌐 Acesso à Aplicação

Após iniciar a aplicação, acesse:

- **URL Principal**: http://localhost:8081
- **Login**: http://localhost:8081/login
- **API Documentation**: http://localhost:8081/swagger-ui.html

### 👤 Usuários Padrão

| Usuário | Senha | Perfil | Acesso |
|---------|-------|--------|--------|
| admin   | 123456 | ADMIN | Sistema completo |
| oper    | 123456 | OPERADOR | Apenas movimentações |

## 📊 Estrutura do Banco de Dados

O sistema utiliza Flyway para controle de versões com as seguintes migrações:

- **V1**: Criação do schema base (tabelas)
- **V2**: Dados iniciais (usuários e papéis)
- **V3**: Dados de exemplo (motos e vagas)
- **V4**: Índices e regras de negócio
- **V5**: Ajustes de enum

## 🔧 Configurações

### Portas
- **Aplicação**: 8081
- **PostgreSQL**: 5432 (padrão)

### Perfis de Segurança
- **ADMIN**: `/moto/**`, `/mov/**`
- **OPERADOR**: `/mov/**`
- **Público**: `/login`, `/css/**`, `/swagger-ui/**`

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar com relatório de cobertura
mvn test jacoco:report
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/mottu/mottuapi/
│   │   ├── config/          # Configurações (Security, Data)
│   │   ├── controller/      # Controladores REST e Web
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── exception/      # Tratamento de exceções
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositórios JPA
│   │   └── service/        # Lógica de negócio
│   └── resources/
│       ├── db/migration/   # Scripts Flyway
│       ├── static/css/     # Recursos estáticos
│       └── templates/      # Templates Thymeleaf
└── test/                   # Testes unitários
```

## 🚨 Solução de Problemas

### Erro de conexão com banco
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais no `application.properties`
- Verifique se o banco `mottu` existe

### Erro de compilação
- Verifique se o Java 21 está instalado
- Execute `mvn clean compile`

### Página não carrega
- Verifique se a aplicação está rodando na porta 8081
- Confirme se não há conflito de portas

## 📝 API Endpoints

### Autenticação
- `POST /login` - Login
- `POST /logout` - Logout

### Motos (ADMIN)
- `GET /moto` - Listar motos
- `GET /moto/novo` - Formulário nova moto
- `POST /moto` - Criar moto
- `GET /moto/editar/{id}` - Formulário editar
- `POST /moto/atualizar/{id}` - Atualizar moto
- `POST /moto/excluir/{id}` - Excluir moto

### Movimentações (ADMIN/OPERADOR)
- `GET /mov` - Listar movimentações
- `GET /mov/checkin` - Formulário check-in
- `POST /mov/checkin` - Realizar check-in
- `GET /mov/checkout` - Formulário check-out
- `POST /mov/checkout` - Realizar check-out

## 👥 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto foi desenvolvido para o Challenge Mottu 2025.

## 📞 Suporte

Para dúvidas ou problemas, entre em contato através dos issues do repositório.

---

**Desenvolvido com ❤️ para o Challenge Mottu 2025**
