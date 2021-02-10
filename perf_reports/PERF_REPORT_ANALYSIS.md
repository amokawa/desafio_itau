# RELATÓRIO DE ANÁLISE DOS TESTES DE PERFORMANCE

## SOLUÇÕES TESTADAS

* Website `www.automationpractice.com`
* API `employee`

## CONCLUSÕES

### Website

* O cenário testado foi o cadastro e fluxo de compra por meio de pagamento em cheque
    * Cadastro de novo usuário
    * Logout
    * Adicionar produto no carrinho
    * Acessar o carrinho
    * Iniciar processo de checkout
    * Realizar autenticação
    * Confirmar endereço
    * Confirmar envio
    * Selecionar meio de pagamanto (cheque)
    * Confirmar pagamento
    * Verificar a frase `Your order on My Store is complete` na página de conclusão de compra
* A simulação do cenário considerou o máximo de 5 usuários simultâneos realizando o fluxo proposto
* Também sobre a simulação, a cada 2 segundos um novo usuário era conectado para realizar o fluxo proposto
* Todas as simulações tiveram a duração de 2 minutos
* Não foi possível testar com mais usuários pois o sistema não suportou, retornando erros com código 508. Estes erros
  foram notados quando foram configurados 10 usuários simultâneos para simulação e também a uma frequência de 1 nova
  conexão de novo usuário a cada 2 segundos.
* Com uma média do índice [APDEX](https://en.wikipedia.org/wiki/Apdex) de 0.031 concluímos que o nível de frustração dos
  usuário é muito alta. O tempo de resposta médio para cada ação em uma amostragem de 270 transações, foi acima de 3
  segundos (para 90% dos casos este tempo foi de mais de 5 segundos)

### API

* O cenário testado foi o CRUD na API de empregado
    * Criar um empregado
    * Buscar empregado
    * Editar empregado
    * Remover empregado
* A simulação considerou o máximo de 5 conexões simultâneas e uma frequência de 1 nova conexão a cada 12 segundos
  durante de 1 minuto
* Náo foi possível obter dados conclusivos sobre o teste de performance
* O sistema não suportou mais de 1 conexão simultânea
* Métodos como de edição e remoção, mesmo em situações onde havia apenas uma conexão em teste, retornaram erro
  (429/Too Many Requests).
* Durante o prazo do teste, houveram cerca de 299 transações onde 194 retornaram o erro mencionado no item anterior e
  100% de erro nas transações dos métodos de removação e a maior taxa de sucesso dentro desta amostragem foi o método
  atualização de empregado, com 4%.
* O [APDEX](https://en.wikipedia.org/wiki/Apdex) dos métodos testados foi de 0.3, porém devem ser desconsiderados as
  médias de 1.0 APDEX para os métodos de criação e atualização, pois como mencionado anteriomente, quase todos as
  tentativas de execução simultâneo resultaram em erro, que por sua vez eram retornados com maior velocidade, elevando
  erroneamente o índice.
* O tempo de resposta médio para 90% da amostragem foi superior a 700 ms.