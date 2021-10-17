# Luizalabs Favorite Products

# Executando o projeto

## Dependências

- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) (opcional)
- [PostgreSQL](https://www.postgresql.org/)

## Banco de Dados

A aplicação depende de um banco de dados **PostgreSQL** para executar.  
Se for utilizar um banco de dados que já existe basta configurar as seguintes propriedades no arquivo de configuração *$PROJECT_ROOT/app/src/main/resources/application.yaml*:

```
spring.datasource.username: $USERNAME
spring.datasource.password: $PASSWORD
spring.datasource.url: jdbc:postgresql://$HOST:$PORT/$DATABASE_NAME?createDatabaseIfNotExist=true
```

É possível também instanciar um banco de dados via **Docker** (recomendado).  
Dessa forma não é necessário alterar nenhuma configuração do projeto.  

```
# executa o docker com o postgresql (baixa a imagem se não tiver)
docker run -d -p 5432:5432 --name postgres-favorite-products -e POSTGRES_PASSWORD=mysecretpassword postgres
```

## Aplicação


# TO-DO

- README com instruções de execução do projeto
- add rest client para a api de produtos
- paginação na busca dos produtos
- oauth
- swagger

