# Projeto Contas para TOTVS

Desafio back end conforme solicitado em [Desafio Backend_v1_202401.pdf](docs%2FDesafio%20Backend_v1_202401.pdf)


Foi criado um arquivo shell script para poder rodar para rodar o projeto e o banco de dados utilizando docker
O próprio arquivo possui uma documentação.


```shell
./workspace.sh up
./workspace.sh down
./workspace.sh recreate
```

* swagger habilitado em http://localhost:8080/swagger-ui.html
* api dosc json em http://localhost:8080/api-docs
* banco de dados exposto na porta 5432 com usuário e senha postgres
