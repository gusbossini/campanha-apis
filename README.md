# campanha-apis
##Arquitetura das aplicações
###campanha-apis
É apenas um agrupador para os 2 módulos que são as aplicações, assim ficando mais simples de abrir o projeto em sua IDE.
###campanha-crud
É um CRUD para campanhas, sistema é acessado via API.
Banco de dados utilizado foi o MySql com usuário "root" e sem senha, com o nome da database "campanha".
Servidor utiliza a porta "8080" para comunicação.
Foi desenvolvido em Spring-Boot e java 8. Para executar aplicação basta fazer o build da mesma utilizando o Maven e com o artefato gerado "*.jar" basta executar com "java -jar *.jar".
Toda a documentação de URLs da aplicação está documentada com Swagger, após executar a aplicação basta acessar o endereço, "localhost:8080/swagger-ui.html"

###campanha-usuario
É uma API utilizada para cadastrar usuários via e-mail acessando algumas informações do campanha-crud, sistema é acessado via API.
Banco de dados utilizado foi o MySql com usuário "root" e sem senha, com o nome da database "usuario".
Servidor utiliza a porta "8081" para comunicação.
Foi desenvolvido em Spring-Boot e java 8. Para executar aplicação basta fazer o build da mesma utilizando o Maven e com o artefato gerado "*.jar" basta executar com "java -jar *.jar".
Toda a documentação de URLs da aplicação está documentada com Swagger, após executar a aplicação basta acessar o endereço, "localhost:8081/swagger-ui.html"

####O código está com alguns java docs caso haja necessidade de maiores informações.
####Todos os testes podem ser feitos através do Swagger nas devidas URLs.