# MicroServiço: Login e Users

MS para login e crud de usuários. Login protegido através da ferramenta SpringSecurity. Seções contraladas através de token. 
<br>
O MS na parte CRUD permite adicionar um complemento com dados adicionais como: endereço, data de nascimento, cpf e etc. Sendo necessário para isso indicar o usuário ao qual terá informações adicionadas.

### Registrar Usuário
``POST: http://localhost:8080/auth/register``
<br>
```
{
    "login":""Teste10",
    "password:"101010",
    "role": "ADMIN"
}
```
### Login
``POST: http://localhost:8080/auth/login``
<br>
```
{
    "login":""Teste1",
    "password:"101010"
}
```
retorno esperado (token temporário)
```
{
    "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6IlRlc3RlMSIsImV4cCI6MTcxMDYwNDY5MX0.G666e1Qz43WkPTIJngxMmQrs8OmjkPqGU_3_2WnDprI"
}
```

### Áreas ADMIN - USERS
Após acessar endpoint em seção autorizada passando o Token no Type:Bearer Token
<br>
```
POST: http://localhost:8080/users/save
GET: http://localhost:8080/users/all
GET: http://localhost:8080/users/byid/{id}
PUT: http://localhost:8080/users/update/{id}
DELETE: http://localhost:8080/users/delete/{id}
```

### Áreas ADMIN - COMPLEMENT
```
POST: http://localhost:8080/complement/save
GET: http://localhost:8080/complement/all
GET: http://localhost:8080/complement/byid/{id}
PUT: http://localhost:8080/complement/update/{id}
DELETE: http://localhost:8080/complement/delete/{id}
```

Salvando Complemento:
<br>
``POST: http://localhost:8080/complement/save``
````
}
    "name": "Carlos Sanches",
    "phone": "(52) 00000-0000",
    "birthday": "2000-01-01",
    "cpf": "87683846023",
    "cep": "61760-905",
    "rua": "Av. Bernardo Manoel",
    "numero": 8600,
    "complemento": "Bloco B",
    "bairro": "Guaribas",
    "cidade": "Eusébio",
    "uf": "CE",
    "user": { "id": 31 }
}
````
retorno esperado (passando token temporário na autorização)
````
{
    "id": 33,
    "name": "Carlos Sanches",
    "phone": "(52) 00000-0000",
    "birthday": "2000-01-01",
    "cpf": "87683846023",
    "cep": "60530-670",
    "rua": "Av. Bernardo Manoel",
    "numero": 8600,
    "complemento": "Bloco B",
    "bairro": "Guaribas",
    "cidade": "Eusébio",
    "uf": "CE",
    "user": {
        "id": 31,
        "login": "Teste1",
        "password": "$2a$10$qp8nKOaStEX2yyU1Vi2Yc.GlwNj5JTE/Y8SstD./tfj9kITumn7Cq",
        "role": "ADMIN"
    }
}
````