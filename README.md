# API Forúm Hub

O challenge Fórum hub faz parte do programa ONE | TECH FOUNDATION - Especialização Back-End + Alura.

O desafio desse challenge é você construir uma API REST usando JAVA e SPRING BOOT.
O 

### Como rodar

Faça uma cópia deste repositório, baixe ou faça um clone-o.

Crie um arquivo chamado ``.env`` na raiz do projeto e adicione o seguinte conteúdo:

```
DATASOURCE_URL= // adicione aqui a url do seu banco
DATASOURCE_USERNAME= // adicione aqui o nome do usuário do seu banco
DATASOURCE_PASSWORD= // adicione aqui o a senha do seu banco
JWT_SECRET= // string necessária na hora de gerar o token JWT
```

No projeto foi utilizado o banco de dados **Mysql** para persistência de dados.

A url do banco pode ser igual essa: ``jdbc:mysql://localhost/nomeDoBanco``, substitua o "nomeDoBanco" pelo banco de dados que você criou, se não criou, crie um.

Abra o projeto na sua IDE, e inicialize o projeto clicando no botão "Run", ou use o atalho CTRL + F10 se a IDE for o Intellij.

Um servidor será aberto na porta 8080. 

Por se tratar de um API, será necessário você usar uma ferramenta de testes de API para testar a api. Você pode usar o **Postman** ou o **Insomnia**.


### EndPoints

**Públicas**

```
/auth/login
/auth/register
/topicos - somente GET
/topicos/** - qualquer rota depois de /topicos, mas somente para o tipo GET
/usuarios/**" - TIPO GET
```

**Privadas**

Todas as outras rotas são privadas. Sendo necessário realizar a autenticação.

```
/topicos - PUT, DELETE e POST
/usuarios - PUT, DELETE
```

### Testando a api

**Autenticação**

**Cadastro de usuário**

```
url: /auth/register


Entrada:
json: 
{
    "nome": " ", // obrigatória
    "email": " ", // obrigatória
    "senha": " "  // obrigatória
}

Saída:
json: 
{
    "id": ,
    "nome": " ",
}

HEADERS
Location : /usuarios/{id} - id do novo usuário 

```

**Login**

```
url: /auth/login

Entrada:

json: 
{
    "email": " ", // obrigatória
    "senha": " " // obrigatória
}

Saída:
{
    "token": " " <- Token JWT
}

```
Um token JWT é retornado, salve em algum local e envie no cabeçalho "Authorization" para acessar outras rotas protegidas. O token JWT possui validade de 2 horas. 


**Cadastro de um tópico**

```
url: /topicos - POST

Entrada:
json: 
{
    "titulo": " ", // obrigatória
    "mensagem": " ", // obrigatória
    "curso": " " // obrigatória
}

Saída:
{
    "id": ,
    "titulo": " ",
    "mensagem": " ",
    "dataCriacao": " "
}

```

**Atualização de um tópico** 

```
url: /topicos/{id} - PUT

{id} // obrigatória, via query String

Entrada: 
json: 
{
    "titulo": " ", // obrigatória
    "mensagem": " ", // obrigatória
    "curso": " " // obrigatória
}

Saída:
{
    "id": ,
    "titulo": " ",
    "mensagem": " ",
    "dataCriacao": " ",
    "status": " ",
    "autor": " ",
    "curso": " "
}

```

**Exclusão de um tópico** 

```
url: /topicos/{id} - DELETE
{id} // obrigatória, via query String
```

**Informações de um tópico**

```
url: /topicos/{id} - GET
{id} // obrigatória, via query String

Saída:
{
    "id": ,
    "titulo": " ",
    "mensagem": " ",
    "dataCriacao": "",
    "status": " ",
    "autor": " ",
    "curso": " "
}
```

**Respostas**

Os tópicos podem conter respostas

**Criando uma resposta**

```
url: /topicos/{id}/respostas - POST

Entrada: 
json

{
    "mensagem": " " / obrigatória
}

Saída: 
json

{
    "id": ,
    "mensagem": " ",
    "dataCriacao": " ",
    "idTopico": ,
    "autor": " "
}

```

**Listando as respostas de um tópico**

```
url: /topicos/{id}/respostas - GET
{id} - id do tópico

Retorno: 
Um json contendo todas os dados da paginação retornado.
```


**Atualizar/Editar uma resposta**

```
url: /topicos/{id}/respostas/{idRespota} - PUT
{id} - id do tópico
{idResposta} - id da resposta

Entrada:
json:

{
    "mensagem": " " / obrigatória
}

Saída:
json

{
    "id": ,
    "mensagem": " ",
    "dataCriacao": " ",
    "idTopico": ,
    "autor": " "
}
```


**Excluir uma resposta**

```
url: /topicos/{id}/respostas/{idRespota} - DELETE
{id} - id do tópico
{idResposta} - id da resposta
```

**Usuário**

**Informações do usuário**

```
url: /usuarios/{id} - GET

Saída: 
json

{
    "id": ,
    "nome": " "
}

```

**Atualizar o nome do usuário**

```
url: /usuarios - PUT

Entrada:
json

{
    "nome": " "
}

Saída:
json

{
    "id": ,
    "nome": " "
}
```

**Excluir um usuário**

```
url: /usuarios - DELETE

Saída:
Sem conteúdo
```

A **exclusão** e a **atualização** do usuário não é preciso passar o id do usuário, verificamos quem é o usuário através do token JWT enviado nas requisições.