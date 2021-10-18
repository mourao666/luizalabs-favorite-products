# Luizalabs Favorite Products

# Executando o projeto

## Dependências

- Java 11
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) (opcional)
- [PostgreSQL](https://www.postgresql.org/)

## Banco de Dados

A aplicação depende de um banco de dados **PostgreSQL** para executar.  
Se for utilizar um banco de dados que já existe basta configurar as seguintes propriedades no arquivo de configuração *$PROJECT_ROOT/app/src/main/resources/application.yaml*:

```
spring
  datasource
  username: $USERNAME
  password: $PASSWORD
  url: jdbc:postgresql://$HOST:$PORT/$DATABASE_NAME?createDatabaseIfNotExist=true
```

É possível também instanciar um banco de dados via **Docker** (recomendado).  
Dessa forma não é necessário alterar nenhuma configuração do projeto.  

```
# executa o docker com o postgresql (baixa a imagem se não tiver)
docker run -d -p 5432:5432 --name postgres-favorite-products -e POSTGRES_PASSWORD=mysecretpassword postgres
```

A aplicação se encarrega de criar a estrutura do banco.  
É possível criar a estrutura manualmente também.  
Basta configurar as propriedades no arquivo de configuração *$PROJECT_ROOT/app/src/main/resources/application.yaml*:

```
spring:
  jpa:
    generate-ddl: false
    hibernate:
      ddl-auto: none
```

E executar o script de criação da estrutura:  
```
$PROJECT_ROOT/database/jpa-postgresql-repository/src/main/resources/create.sql
```

## Aplicação

Para compilar o projeto execute o comando na raiz do projeto:
```
mvn clean install
```

Para executar o projeto execute o comando na raiz do projeto:
```
mvn -f ./app/pom.xml spring-boot:run
```
Para executar o projeto em modo *debug* (é necessário anexar um *debugger* na porta 5005):
```
mvn -f ./app/pom.xml spring-boot:run  -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```
Alternativamente é possível executar o projeto com o comando:
```
java -jar ./app/target/favorite-products.jar
```

# TO-DO

- README com instruções de execução do projeto
- oauth
- swagger
- Logging

