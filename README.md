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
/login
/cadastro
/topicos - somente GET
/topicos/** - qualquer rota depois de /topicos, mas somente para o tipo GET
```

**Privadas**

Todas as outras rotas são privadas. Sendo necessário realizar a autenticação.

```
/topicos - PUT, DELETE e POST
```

### Testando a api

**Cadastro de usuário**

```
url: /cadastro


Entrada:
json: 
{
    "nome": " ", // obrigatória
    "email": " ", // obrigatória
    "senha": " "  // obrigatória
}

Saída:

// Sem retorno, por enquanto.
```

**Login**

```
url: /login

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
    "autor": " ", // obrigatória
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
