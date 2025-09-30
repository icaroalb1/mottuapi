# 🚀 Instalação Rápida - Mottu API

## Pré-requisitos
- Java 21+
- Maven 3.6+
- PostgreSQL 12+

## 1. Configurar Banco de Dados
```sql
CREATE DATABASE mottu;
CREATE USER mottu_user WITH PASSWORD 'mottu_password';
GRANT ALL PRIVILEGES ON DATABASE mottu TO mottu_user;
```

## 2. Executar Aplicação
```bash
# Clone o repositório
git clone <URL_DO_REPOSITORIO>
cd mottuapi-1

# Compilar e executar
mvn clean spring-boot:run
```

## 3. Acessar Sistema
- **URL**: http://localhost:8081
- **Login**: admin / 123456 (ADMIN)
- **Login**: oper / 123456 (OPERADOR)

## 4. Executar com Docker (Opcional)
```bash
# Subir banco e aplicação
docker-compose up -d

# Acessar: http://localhost:8081
```

## 5. Executar Testes
```bash
mvn test
```

## 📋 Funcionalidades Disponíveis

### Para ADMIN:
- ✅ Gerenciar motos (CRUD completo)
- ✅ Gerenciar movimentações
- ✅ Acessar todas as funcionalidades

### Para OPERADOR:
- ✅ Visualizar movimentações
- ✅ Realizar check-in/check-out
- ❌ Não pode gerenciar motos

## 🔧 Solução de Problemas

### Erro de conexão com banco:
1. Verifique se PostgreSQL está rodando
2. Confirme credenciais no `application.properties`
3. Verifique se banco `mottu` existe

### Erro de compilação:
1. Verifique Java 21: `java -version`
2. Execute: `mvn clean compile`

### Porta ocupada:
1. Mude porta no `application.properties`
2. Ou mate processo: `lsof -ti:8081 | xargs kill -9`
