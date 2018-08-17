# campanha-apis
## Arquitetura das aplicações
### campanha-apis
É apenas um agrupador para os 2 módulos que são as aplicações, assim ficando mais simples de abrir o projeto em sua IDE.

### campanha-crud
1. É um CRUD para campanhas, sistema é acessado via API.
2. Banco de dados utilizado foi o MySql com usuário "root" e sem senha, com o nome da database "campanha".
3. Servidor utiliza a porta "8080" para comunicação.
4. Foi desenvolvido em Spring-Boot e java 8. Para executar aplicação basta fazer o build da mesma utilizando o Maven e com o artefato gerado "*.jar" basta executar com "java -jar *.jar".
5. Toda a documentação de URLs da aplicação está documentada com Swagger, após executar a aplicação basta acessar o endereço, "localhost:8080/swagger-ui.html"

### campanha-usuario
1. É uma API utilizada para cadastrar usuários via e-mail acessando algumas informações do campanha-crud, sistema é acessado via API.
2. Banco de dados utilizado foi o MySql com usuário "root" e sem senha, com o nome da database "usuario".
2.1. Foi decidido utilizar outro database porque as aplicações devem funcionar de forma autônoma uma da outra.
3. Servidor utiliza a porta "8081" para comunicação.
4. Foi desenvolvido em Spring-Boot e java 8. Para executar aplicação basta fazer o build da mesma utilizando o Maven e com o artefato gerado "*.jar" basta executar com "java -jar *.jar".
5. Toda a documentação de URLs da aplicação está documentada com Swagger, após executar a aplicação basta acessar o endereço, "localhost:8081/swagger-ui.html"

#### O código está com alguns java docs caso haja necessidade de maiores informações.
#### Todos os testes podem ser feitos através do Swagger nas devidas URLs.