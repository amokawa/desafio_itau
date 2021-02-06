@api
Feature: Testes de API relacionados aos métodos do endpoint de '/photos'
  - GET https://jsonplaceholder.typicode.com/photos

  Scenario: Deve baixar a imagem utilizando o campo URL
    When uma requisição "GET" é efetuada para "https://jsonplaceholder.typicode.com/photos/1234"
    Then o status da resposta é 200
    Then a imagem deve ser baixada usando o campo "url"

  Scenario: Deve baixar a imagem utilizando o campo thumbnailUrl
    When uma requisição "GET" é efetuada para "https://jsonplaceholder.typicode.com/photos/2345"
    Then o status da resposta é 200
    Then a imagem deve ser baixada usando o campo "thumbnailUrl"