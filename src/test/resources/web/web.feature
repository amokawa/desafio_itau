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
    Given um cliente com dados gerados randomicamente
    And a página "sign in" é carregada
    When o cadastro de usuário é enviado com todos os campos obrigatórios preenchidos com dados válidos
    Then o nome e sobrenome do cliente deve ser exibido no cabeçalho

  Scenario: Deve exibir o nome e sobrenome do usuário realizar login com email e senha válidos
    Given um cliente com dados gerados randomicamente
    And a página "sign in" é carregada
    When as credenciais "asdfasdf@email.com" e "password" são enviadas
    Then o nome e sobrenome do cliente deve ser exibido no cabeçalho

  Scenario: Os itens podem ser alterados na página de Resumo de compras
    Given a página "home" é carregada
    And são adicionados alguns itens no carrinho
      | Faded Short Sleeve T-shirts |
      | Printed Chiffon Dress       |
    And a página "order" é carregada
    When a quantidade do produto "Faded Short Sleeve T-shirts" é alterada para 2
    Then na página de Resumo de compras o valor de "TOTAL" deve ser atualizado

  Scenario: O valor total deve ser alterado ao remover um item da página de Resumo de compras
    Given a página "home" é carregada
    And são adicionados alguns itens no carrinho
      | Faded Short Sleeve T-shirts |
      | Printed Chiffon Dress       |
    And a página "order" é carregada
    When o produto "Faded Short Sleeve T-shirts" é removido
    Then na página de Resumo de compras o valor de "TOTAL" deve ser atualizado

  Scenario: Deve ser possível efetuar compras via transferência bancária
    Given um cliente com dados gerados randomicamente
    And a página "sign in" é carregada
    And o cadastro de usuário é enviado com todos os campos obrigatórios preenchidos com dados válidos
    And a página "home" é carregada
    And são adicionados alguns itens no carrinho
      | Faded Short Sleeve T-shirts |
      | Printed Chiffon Dress       |
    And a página "order" é carregada
    When a opção de pagamento via "transferência bancária"
    Then a página de confirmação de compra é exibida a mensagem de sucesso

  Scenario: Deve ser possível efetuar compras na função cheque
    Given um cliente com dados gerados randomicamente
    And a página "sign in" é carregada
    And o cadastro de usuário é enviado com todos os campos obrigatórios preenchidos com dados válidos
    And a página "home" é carregada
    And são adicionados alguns itens no carrinho
      | Faded Short Sleeve T-shirts |
      | Printed Chiffon Dress       |
    And a página "order" é carregada
    When a opção de pagamento via "cheque"
    Then a página de confirmação de compra é exibida a mensagem de sucesso