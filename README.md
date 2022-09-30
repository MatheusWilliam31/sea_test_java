# sea_test_java
Project Test Java For SEA

## Regras de Negocio 
- Não deve ser possível cadastrar um setor com o mesmo nome de outro existente;

- Cargos ficam vinculados a um setor e não podem ser cadastrados em outros setores;

- Um trabalhador está vinculado a um setor e a um cargo;

- Não é possível ter dois trabalhadores com mesmo CPF;

## Tecnologias utilizadas
- Java 8
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Liquibase
- Docker
- Docker compose
- OpenAPI/Spring Docs

### Instalação com Docker Compose
```sh
git clone https://github.com/MatheusWilliam31/sea_test_java.git
cd docker/container
docker-compose up
```
