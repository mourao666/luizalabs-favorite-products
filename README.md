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

## Segurança

A aplicação está configurada com uma camada de autenticação/autorização OAuth2.  
Por padrão a segurança está habilitada.  
Para desabilitar a segurança é necessário alterar a seguinte propriedade no arquivo de configuração *$PROJECT_ROOT/app/src/main/resources/application.yaml*:
```
spring:
  security:
    oauth2:
      enable: false
```

Para acessar os recursos da aplicação é necessário configurar um *client* no servidor de autorização.

### Servidor de autorização

#### Instanciando o servidor

Para compilar o projeto execute o comando na raiz do projeto:
```
mvn clean install
```

Para executar o projeto execute o comando na raiz do projeto:
```
mvn -f ./authorization/pom.xml spring-boot:run
```
Para executar o projeto em modo *debug* (é necessário anexar um *debugger* na porta 5005):
```
mvn -f ./authorization/pom.xml spring-boot:run  -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```
Alternativamente é possível executar o projeto com o comando:
```
java -jar ./authorization/target/authorization-server.jar
```

#### Configurando o client

Os seguintes passos podem ser encontrado também nesse [tutorial](https://www.baeldung.com/spring-boot-keycloak).  

Com o servidor de autorização instanciado acesse via *browser* o endereço http://localhost:8180/auth/
![tela inicial do servidor de autorizacao](documentation/images/initial-screen.png)

Clique na caixa **Administration Console** e entre com as credenciais *admin/adminPass*:
![tela de login](documentation/images/login-screen.png)

Clicando no botão **Log In** você será redirecionada para a área administrativa:
![area administrativa](documentation/images/administrative-area.png)

No canto superior esquerdo, passando o *mouse* sobre a área **Master** irá aparecer o botão para adicionar um domínio (*Realm*):
![criar dominio](documentation/images/create-realm-button.png)

Clicando no botão **Add realm** você será redirecionado para a tela onde é possível adicionar um novo domínio.  
Entre com o nome do domínio **FavoriteProducts** e clique no botão **Create**
![adicionar dominio](documentation/images/add-realm-screen.png)

Agora é necessário criar um *client*.  
No canto esquerdo clique no botão **Clients** e irá aparecer a lista de *clients*.  
No canto direito clique no botão **Create**:
![criar cliente](documentation/images/create-client-screen.png)

Na próxima tela entre com o **ID** do cliente '*client-app*' e clique no botão **Save**
![criar cliente 2](documentation/images/create-client-screen2.png)

Você será redirecionado para a tela de configuração do cliente.  
Entre com a url de redirecionamento permitida e clique no botão **Save**
![configurar cliente](documentation/images/configure-client-screen.png)

Agora é necessário criar uma regra.  
No canto esquerdo clique no botão **Roles** e irá aparecer a lista de *roles*.  
No canto direito clique no botão **Add role**:  
![criar regra](documentation/images/add-role-screen.png)

Entre com o nome da regra '*user*' e clique no botão **Save**  
![criar regra 2](documentation/images/add-role-screen2.png)

Agora é necessário criar um usuário.  
No canto esquerdo clique no botão **Users** e irá aparecer a lista de *usuários*.  
No canto direito clique no botão **Add user**:  
![criar usuario](documentation/images/add-user-screen.png)

Entre com o nome do usuário '*user1*' e clique no botão **Save**  
![criar usuario 2](documentation/images/add-user-screen2.png)

Agora na aba **Credentials** configure a senha do usuário.  
Desmarque a opção **Temporary** para não ser necessário resetar a senha no primeiro acesso  
![configurar usuario](documentation/images/configure-user-screen.png)

Agora na aba **Role Mappings** adicione a regra previamente criada para o usuário.  
Selecione a regra e cliqueno botão **Add selected** na caixa da esquerda  
![adicionar regra para o usuario](documentation/images/add-role-to-user.png)

Agora basta acessar o servidor de autorização com as credenciais criadas.  

#### Obtendo acesso ao servidor de recurso

Para obter acesso ao servidor de recurso acesse o servidor de autorização no seguinte *endpoint*:  
```
curl --location --request POST 'http://localhost:8180/auth/realms/FavoriteProducts/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=client-app' \
--data-urlencode 'username=user1' \
--data-urlencode 'password=user1Pass' \
--data-urlencode 'grant_type=password'
```

O servidor de autorização irá responder com o *token* de acesso e o token de *refresh*:  
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaU2hQRHlvNXVQMTlXMEZ3MFpIa0lkdV9hSVBFOW04MDlTRDAzWFUzdlRnIn0.eyJleHAiOjE2MzQ1NzI3ODUsImlhdCI6MTYzNDU3MjQ4NSwianRpIjoiOTBiMTA2ZWUtYjc1Zi00MjRhLWJlNzktNDc1NDMzOWMwNWM1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL0Zhdm9yaXRlUHJvZHVjdHMiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNjE3YWE4MjktOTA3Ny00ODM2LTk4OWQtZmY5ODkwYjY2MDY4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY2xpZW50LWFwcCIsInNlc3Npb25fc3RhdGUiOiIzNjRkNGJmYS1mYTY3LTQ2OGYtOWQ4ZC01ZjBhZGNlOGQ2OTIiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyMSJ9.UVNPnWwghToKhuiZx7XTogsg7jt8QLDVQmg5wzdYZ11FTBFc68_uxv79TzPxYpQCgipkDZPF4BV_9XJAaUvbWwTu-bai0HDyX_A-9ytrdLvbNNniz9n_bIN0U9e_cwmqcmlboisZDgQ8Ec75wMCOK9Wk-5fOGWvUOTkH-8Xi1dE2aBjh59nO6tdGQ549m14uQMJHr7azIJEdGWVEPGRssZeuv3XCblhYgOyjWubWnbX3NClj3msxn46FmStuu6fIW1roz1QFq1O-AabcItaRfkTuvASDgioLZ0CnyzsFR4iBws-ynXHSfHxdr1Eeir6r6qPjnjfvFDg5hjFxYbScdQ",
    "expires_in": 300,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlYWRhNDgzMi1kMTA0LTQ3OTAtOTYzNy1kYTAyYzQ2OGIyMTUifQ.eyJleHAiOjE2MzQ1NzQyODUsImlhdCI6MTYzNDU3MjQ4NSwianRpIjoiNTIwZmRjNDEtMmM4MS00ZmRhLWJlYTQtYWY1MjQyOTZlNmM2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL0Zhdm9yaXRlUHJvZHVjdHMiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvRmF2b3JpdGVQcm9kdWN0cyIsInN1YiI6IjYxN2FhODI5LTkwNzctNDgzNi05ODlkLWZmOTg5MGI2NjA2OCIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJjbGllbnQtYXBwIiwic2Vzc2lvbl9zdGF0ZSI6IjM2NGQ0YmZhLWZhNjctNDY4Zi05ZDhkLTVmMGFkY2U4ZDY5MiIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCJ9.vPYN05MO34NCXT47m12-u6yp2bP9hd7csplL-QgwFyU",
    "token_type": "bearer",
    "not-before-policy": 0,
    "session_state": "364d4bfa-fa67-468f-9d8d-5f0adce8d692",
    "scope": "profile email"
}
```

Se o token de acesso expirar, basta chamar o endpoint de *refresh token* passando o *token* de *refresh* retornado no serviço anterior.  
Ele irá responder com a mesma estrutura:
```
curl --location --request POST 'http://localhost:8180/auth/realms/FavoriteProducts/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=client-app' \
--data-urlencode 'refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlYWRhNDgzMi1kMTA0LTQ3OTAtOTYzNy1kYTAyYzQ2OGIyMTUifQ.eyJleHAiOjE2MzQ1NzQyODUsImlhdCI6MTYzNDU3MjQ4NSwianRpIjoiNTIwZmRjNDEtMmM4MS00ZmRhLWJlYTQtYWY1MjQyOTZlNmM2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL0Zhdm9yaXRlUHJvZHVjdHMiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvRmF2b3JpdGVQcm9kdWN0cyIsInN1YiI6IjYxN2FhODI5LTkwNzctNDgzNi05ODlkLWZmOTg5MGI2NjA2OCIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJjbGllbnQtYXBwIiwic2Vzc2lvbl9zdGF0ZSI6IjM2NGQ0YmZhLWZhNjctNDY4Zi05ZDhkLTVmMGFkY2U4ZDY5MiIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCJ9.vPYN05MO34NCXT47m12-u6yp2bP9hd7csplL-QgwFyU' \
--data-urlencode 'grant_type=refresh_token'
```

#### Acessando o servidor de recurso

Para acessar os recursos da aplicação de **Produtos Favoritos** basta chamar os *endpoints* passando o token de acesso no cabeçalho *Authorization: Bearer $ACCESS_TOKEN*:  
```
curl --location --request GET 'http://localhost:8080/v1/customers/87ade134-0eac-4111-b528-5909fa30d2cf' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaU2hQRHlvNXVQMTlXMEZ3MFpIa0lkdV9hSVBFOW04MDlTRDAzWFUzdlRnIn0.eyJleHAiOjE2MzQ1NzI5NzAsImlhdCI6MTYzNDU3MjY3MCwianRpIjoiMDU2ZjRiOGYtN2VhMy00MzkxLTk4MTUtODAwNmJiNTFlYTViIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL0Zhdm9yaXRlUHJvZHVjdHMiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNjE3YWE4MjktOTA3Ny00ODM2LTk4OWQtZmY5ODkwYjY2MDY4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY2xpZW50LWFwcCIsInNlc3Npb25fc3RhdGUiOiIzNjRkNGJmYS1mYTY3LTQ2OGYtOWQ4ZC01ZjBhZGNlOGQ2OTIiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyMSJ9.OZEUSWR-X-X0kT8A0v1G_PhMW5y3B6VXPJNNgRuyAghzZ-gB-nq7TOCaBhZNVoGRbWtDgpWOST65iIYAOo4cmA4wqi9vWpyrRQDmhfyScqHK1Ey47KcRy9w7sY42hgNDEOLyhYTfA6EJeeNPTM5pvMf-zIG6l6LqxH1x9vLFY13dNRgrAwtvrDaQFxAEy896EUPAIbRJjQ4dgnItx0oh9YbLPniuJScvBAcAmSGKfj67Chp42mbDUMX21YcWY33V1_tNpRpETR1-3RsN-DHrXtsnEq_0vc2mmdfrC2cpR9e-5x5uHX9CAQ39-YrtrUnDqoky8zo_3VfTrvgqUcQSMw' \
--header 'Cookie: JSESSIONID=10941A55A81AF8ED4C36DAD9DDF23AA9'
```

## Postman

Junto com o projeto há as coleções e ambientes do **Postman** para facilitar os testes.  
Está no caminho *$PROJECT_ROOT/documentation/postman/*  

# TO-DO

- README com instruções de execução do projeto
- swagger
- Logging

