# QRCode Service

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![AWS](https://img.shields.io/badge/Amazon%20AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/docker%20compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Uma aplicação Spring Boot para gerar e gerenciar códigos QR. O serviço oferece endpoints para criar e recuperar códigos QR, com armazenamento em MongoDB e integração com AWS S3.

## 📋 Pré-requisitos

- **Java 25** ou superior
- **Maven 3.8+**
- **Docker** e **Docker Compose**
- **MongoDB** (pode ser executado via Docker Compose)
- **AWS LocalStack** (para testes locais, pode ser executado via Docker Compose)

## 🚀 Como Executar

### 1. Iniciando Docker Compose

```bash
# Iniciar os serviços (MongoDB e LocalStack)
docker compose up -d
```

### 2. Executar

```bash
# Compilar e executar
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

A aplicação estará disponível em `http://localhost:8080`


## 📝 Configuração

A aplicação utiliza diferentes perfis de configuração:

- **application.yaml** - Configuração padrão
- **application-local.yaml** - Configuração para desenvolvimento local
- **application-test.yaml** - Configuração para testes


## 🛠️ Tecnologias Utilizadas

- **Spring Boot** - Framework web e gerenciamento de dependências
- **Spring Data MongoDB** - Persistência de dados
- **ZXing** - Geração de códigos QR
- **AWS SDK** - Integração com AWS S3
- **MongoDB** - Banco de dados NoSQL
- **Docker & Docker Compose** - Containerização e orquestração

## 📚 API Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/v1/ping` | Health check |
| POST | `/v1/qr` | Criar QR Code |
| GET | `/v1/qr/{id}` | Recuperar QR Code |

## 🧪 Testes

Executar todos os testes:

```bash
./mvnw test
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/julioceno/qrcodeservice/
│   │   ├── adapters/
│   │   │   ├── in/        # Controladores e Filters
│   │   │   └── out/       # Adaptadores de saída
│   │   ├── core/
│   │   │   ├── application/  # Casos de uso e serviços
│   │   │   ├── domain/       # Entidades e interfaces
│   │   └── infrastructure/   # Configurações e integrações
│   └── resources/
│       └── application*.yaml # Configurações
└── test/
    └── java/com/julioceno/qrcodeservice/
        └── Testes
```

## 📊 Monitoramento

A aplicação inclui filtro de Correlation ID para rastreamento de requisições através de logs.

## 🔒 Segurança

- Validação de entrada em todos os endpoints
- Tratamento seguro de exceções
- Isolamento de dados por correlação de requisição

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob licença MIT. Veja o arquivo [LICENSE](./LICENSE) para mais detalhes.