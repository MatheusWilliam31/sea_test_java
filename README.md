# Test Java SEA
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

### Para testar endpoints
- Setor
```json
{
  "id": 2,
  "name": "RH"
}
```
- Cargo
```json
{
  "id":2,
  "name": "Rh Pleno",
  "sector": {
    "id": 1
  }
}
```
- Trabalhador
```json
{
  "id":5,
  "name": "Matheus",
  "cpf": "649.777.170-07",
  "role": {
    "id":1
  },
  "sector": {
    "id": 1
  }
}

```

### Instalação com Docker Compose
```sh
git clone https://github.com/MatheusWilliam31/sea_test_java.git
cd docker/container
docker-compose up
```
