@web
Feature: Testes em http://automationpractice.com/index.php

  Scenario: Verificar se página home é carregada com todos os elementos
    When a página "home" é carregada
    Then todos os elementos são carregados

  Scenario: O carrinho deve exibir o mesmo número de itens adicionados
    Given a página "home" é carregada
    When são adicionados alguns itens no carrinho
      | Faded Short Sleeve T-shirts |
      | Printed Chiffon Dress       |
    Then o carrinho deve ter 2 produto(s)

  Scenario: Quando um item é removido do carrinho o número de itens a comprar deve ser atualizado
    Given a página "home" é carregada
    And são adicionados alguns itens no carrinho
      | Faded Short Sleeve T-shirts |
      | Printed Chiffon Dress       |
    When o item 2 é removido do carrinho
    Then o carrinho deve ter 1 produto(s)

  Scenario: Deve exibir o nome e sobrenome do usuário após enviar o formulário de cadastro com dados válidos

  Scenario: Deve exibir o nome e sobrenome do usuário realizar login com email e senha válidos

  Scenario: Deve exibir a lista de itens ao enviar o campo de busca com nome de item desejado

  Scenario: Os itens podem ser alterados na página de Resumo de compras

  Scenario: O valor total deve ser alterado ao remover um item da página de Resumo de compras

  Scenario: O valor total deve ser alterado ao remover um item da página de Resumo de compras

  Scenario: Deve ser possível efetuar compras na função débito

  Scenario: Deve ser possível efetuar compras na função cheque
