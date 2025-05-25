# MottuAPI - Challenge Java Advanced 2025

API REST desenvolvida para o desafio integrador da empresa **Mottu**, como parte do Challenge FIAP 2025 - 1º semestre. A solução realiza o mapeamento e controle de motos em pátios, com sensores simulados, controle de localização e gestão de vagas.

## 👨‍💻 Desenvolvedor

- Icaro Albuquerque (RM555131)
- Guilherme Paes Camargo (RM555166
- Bianca Vitoria Galo Monteiro (RM556270)

## 🚀 Tecnologias utilizadas

- Java 21  
- Spring Boot 3.4.5  
- Spring Data JPA  
- Spring Validation  
- Spring Cache  
- Banco de Dados H2  
- Swagger UI (SpringDoc OpenAPI)  
- Maven

## 📦 Funcionalidades

- ✅ Cadastro e gerenciamento de **motos**  
- ✅ Controle de **vagas** (ocupar/liberar)  
- ✅ Registro e histórico de **localizações**  
- ✅ Simulação de **sensor**  
- ✅ Paginação e ordenação com `Pageable`  
- ✅ **Cache** na listagem de motos  
- ✅ Tratamento global de exceções  
- ✅ Validações com Bean Validation

## 🧪 Como testar a API

### ▶️ Executar o projeto

```bash
git clone https://github.com/icaroalb1/mottuapi.git
cd mottuapi
./mvnw spring-boot:run

💡 Requer: Java 21 instalado

🔍 Acesso ao Swagger
Após iniciar o projeto, acesse:

http://localhost:8081/swagger-ui/index.html
