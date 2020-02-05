# Star Wars API - Planetas
REST API para manipular os planetas da Stars wars


## Tecnologias utilizadas

* JAVA 1.8
  * Spring boot 2.2.4
* MongoDB 3.11.2
* Maven 
  
## Estrutura da API

As operações CRUD sob os planetas, fizeram que ache como a solução mais simples e segura a de **usar como  endpoint um repositorio**. Deste jeito consegui que a API use HATEOAS, o que pode ser importante para uma API REST.

### TODO List

* Uso de SWAPI: Fiz um cliente para consumir a quantidade de films por planeta. Só que o SWAPI retorna esses filmes num *LinkedHashMap* e não conseguí usar ele para obter uma lista de filmes. Vou continuar pesquisando isso. No caso de ter conseguido a lista com os filmes, seguramente para o **add do planeta** deveria trocar o uso direto do repository por uma arquitetura de serviço e controller para adicionar essa funcionalidade.
* Integration test
* Uso de docker

## MongoDB considerações
A DB é a que o springboot usa por default **test**
A collection **planet** precisa de criar um index unique para que não se criem mais de um planeta com o mesmo nome. Esse index foi criado no mongoDB:

*db.planet.createIndex( { "name": 1 }, { unique: true } )*

## Testes manuais (usei POSTMAN)

### Create Planet SUCCESS

**POST - http://localhost:8080/planets**

![add planet](/images/addPlanetsuccess.jpg)

### Create Planet ERROR DUPLICATE PLANET

**POST - http://localhost:8080/planets**

![add planet error](/images/addPlanetError.jpg)

### Listar Planetas

**GET - http://localhost:8080/planets**

![get planet](/images/listarPlanetas.jpg)

### Buscar planeta por Nome

**GET - http://localhost:8080/planets/search/findByName?name=Bespin**

![get planet by name](/images/findByName.jpg)

### Buscar planeta por ID

**GET - http://localhost:8080/planets/5e3ad071407f54044baa3a29**

![get planet by id](/images/findById.jpg)

### Buscar planeta por ID

**DELETE - http://localhost:8080/planets/5e3ad071407f54044baa3a29**

![del planet by id](/images/deletePlanet.jpg)








