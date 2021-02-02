# Plano de teste de interface web

## Escopo
* Os testes serão realizados em um site de compras na visão do usuário final, doravante 
  chamado de comprador.
* Serão priorizados os testes de fluxo de compra de produtos e cadastro de usuário.
* Serão realizados os testes automatizados para os casos de testes funcionais e de interface 
  para o domínio http://automationpractice.com.
* O navegador (driver) utilizado será o Google Chrome (chromedriver) em sua versão mais 
  recente.
* Qualquer link que leve a um domínio diferente ao especificado anteriormente, não será 
  testado.
* Testes não funcionais, cito como exemplo os testes exploratórios e 
  testes de performance, assim como acesso ao banco de dados estão fora de escopo.
* Qualquer defeito encontrado será apresentado em um documento com título 
  [**Bugs e melhorias**](BUGS_E_MELHORIAS.md).

## Artefatos
* Plano de teste (este documento)
* [Casos de teste](web.feature)
* [Bugs e melhorias](BUGS_E_MELHORIAS.md)
* [Relatório de testes](https://reports.cucumber.io/reports/e0c5353d-7eaa-482a-8283-017926980265) - gerado 
  dinameicamente após a execução dos testes utilizando o [Cucumber reports service](https://reports.cucumber.io/),
  e os relatórios ficam salvos no [Cucumber reports collection através deste 
  link](https://reports.cucumber.io/report-collections/b216cdd2-25d5-4b38-a389-36403f5f4cee).

## Recursos

### Ferramentas e técnicas
* Selenium webdriver (3.141.59)
* [ChromeDriver (87.0.4280.88)](https://chromedriver.storage.googleapis.com/index.html?path=87.0.4280.88/)
* Page objects (selenium)
* Page factory (selenium)
* Cucumber (6.8.1)

### Ambiente
* OS Name	Microsoft Windows 10 Enterprise
* Processor	Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz, 2808 Mhz, 2 Core(s), 4 Logical 
  Processor(s)
