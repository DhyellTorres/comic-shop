﻿# Comic Shop API

Esta é uma API REST para uma loja de quadrinhos, criada para um Desafio Técnico Back-end, com a finalidade de contratação no Processo Seletivo da SQUAD DIGITAL. O objetivo do desafio é fornecer recursos para listar, cadastrar, atualizar e excluir quadrinhos, além de outras funcionalidades adicionais.

Nesta sulução proposta tem como objetivo fornecer resursos para:
  * listar, cadastrar, atualizar, excluir quadrinhos;
  * listar, cadastrar, atualizar, excluir cupons de desconto;
  * listar, cadastrar(realizar), atualizar, excluir compras (encomendas de quadrinhos).

## Tecnologias Utilizadas

- Spring Boot 3.1: Framework para construção de aplicativos Java baseados na plataforma Spring.
- PostgreSQL: Banco de dados relacional para armazenar os dados da aplicação.
- JWT (JSON Web Tokens): Mecanismo de autenticação baseado em tokens.
- Swagger: Ferramenta para documentação de API.
- JUnit: Framework de testes unitários para garantir a qualidade do código.

## Arquitetura e Funcionamento

A API segue uma arquitetura modular baseada no padrão MVC (Model-View-Controller).

- Os controladores (`*Controller.java`) são responsáveis por receber as requisições HTTP, processar a lógica de negócio correspondente e retornar as respostas adequadas.
- Os serviços (`*Service.java`) encapsulam a lógica de negócio e interagem com os repositórios para acessar e manipular os dados.
- Os modelos (`Model.java`) representam as entidades de domínio e são responsáveis por mapear os dados do banco de dados em objetos Java.

### Endpoints Disponíveis

A API oferece os seguintes endpoints:

- Comics:
  - `GET /api/v1/comics`: Lista todos os quadrinhos disponíveis.
  - `GET /api/v1/comics/{id}`: Retorna os detalhes de um quadrinho específico.
  - `GET /api/v1/comics/search`: Retorna os detalhes de um quadrinho específico pelo titulo.
  - `GET /api/v1/comics/searchByCreator`: Retorna a lista de quadrinho do escritor especificado.
  - `GET /api/v1/comics/page/{page}`: Lista os quadrinhos disponíveis em paginas de 20.
  - `POST /api/v1/comics`: Cria um novo quadrinho.
  - `PUT /api/v1/comics/{id}`: Atualiza as informações de um quadrinho existente.
  - `DELETE /api/v1/comics/{id}`: Exclui um quadrinho.

- Coupon
  - `GET /api/v1/coupons`: Lista todos os cupons disponíveis.
  - `GET /api/v1/coupons/{id}`: Retorna os detalhes de um cupom específico.
  - `GET /api/v1/coupons/code/{code}`: Retorna os detalhes de um cupom específico pelo codigo.
  - `POST /api/v1/coupons`: Cria um novo cupom.
  - `PUT /api/v1/coupons/{id}`: Atualiza as informações de um cupom existente.
  - `DELETE /api/v1/coupons/{id}`: Exclui um cupom.

- Shopping:
  - `GET /api/v1/shopping`: Lista todas as compras feitas.
  - `GET /api/v1/shoppings/coupon/{coupon_id}`: Lista todas as compras feitas utilizando um cupom específico.
  - `GET /api/v1/shoppings/comic/{comic_id}`: Lista todas as compras feitas de um quadrinho específico.
  - `GET /api/v1/shoppings/user/{user_email}`: Lista todas as compras feitas por um usuário específico.
  - `GET /api/v1/shoppings/user/{user_email}/coupon/{coupon_id}`: Lista compra de um usuário e um cupom específicos.
  - `POST /api/v1/shoppings`: Realiza compra.
  - `POST /api/v1/shoppings/coupon`: Realiza compra usando um cupom.
  - `PUT /api/v1/shoppings/{id}`: Atualiza uma compra existente.
  - `DELETE /api/v1/shoppings/{id}`: Exclui uma compra.

- Authentication:
  - `POST /api/v1/auth/register`: Registra usuário de atenticação de segurança.
  - `POST /api/v1/auth/refresh-token`: Atualiza o token.
  - `POST /api/v1/auth/authenticate`: Login de usuário de atenticação de segurança.

## Configuração e Execução

Para executar a API em sua máquina local, siga estas etapas:

1. Certifique-se de ter o JDK 17+ e o Maven 3+ instalados em seu sistema.
2. Clone o repositório `https://github.com/DhyellTorres/comic-shop.git` e navegue até o diretório raiz do projeto.
3. Configure as informações do banco de dados no arquivo `application.yml`.
4. Execute o seguinte comando para construir o projeto:
  ``` mvn clean install ```
5. Execute o seguinte comando para iniciar a API:
  ``` mvn spring-boot:run ```
6. A API estará acessível em `http://localhost:8080`.

## Testes

Os testes unitários são implementados utilizando o framework JUnit. Para executar os testes, utilize o comando:
  ``` mvn test ```

Os testes visam garantir a qualidade do código e a corretude das funcionalidades implementadas.

## Documentação da API

A documentação completa da API, incluindo todos os endpoints, parâmetros e exemplos de requisição e resposta, podeser encontrada na documentação gerada pelo Swagger. Para acessar a documentação, inicie a API e acesse o Swagger UI em `http://localhost:8080/swagger-ui.html` no seu navegador.

## Considerações Finais

Este projeto implementa uma API para uma loja de quadrinhos, utilizando tecnologias como Spring Boot, PostgreSQL e uma arquitetura modular com o padrão MVC. A solução atende aos requisitos do desafio proposto, oferecendo funcionalidades básicas de CRUD para quadrinhos, além de recursos adicionais como autenticação JWT, modificação de raridade, cupons de desconto e submissão de encomendas.

O código é organizado, seguindo os princípios de desenvolvimento limpo e boas práticas de programação. A documentação é fornecida tanto no README quanto no Swagger, facilitando o entendimento e a utilização da API.

Sinta-se à vontade para entrar em contato caso tenha alguma dúvida ou necessite de mais informações. Aproveite e divirta-se desenvolvendo sua loja de quadrinhos!
