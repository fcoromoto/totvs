# Projeto Contas para TOTVS

Desafio back end conforme solicitado em ```docs/Desafio Backend_v1_202401.pdf```


Foi criado um arquivo shell script para poder rodar para rodar o projeto e o banco de dados utilizando docker
O próprio arquivo possui uma documentação.


```shell
./workspace.sh up
./workspace.sh down
./workspace.sh recreate
```

* swagger habilitado em http://localhost:8080/swagger-ui/index.html
* api dosc json em http://localhost:8080/api-docs
* banco de dados exposto na porta 5432 com usuário e senha postgres
* Deve-se registrar antes de poder 
* arquivo http requests para testar:
    * APIS auth ```request/auth.http```
    * APIS de contas ```request/contas.http```
      * Para as chamadas de contas deve-se pegar o token gerado no login e colocar no header Authorization
    * APIS swagger ```request/swagger.http```
