# Plano de teste

## Escopo
* Os testes serão realizados em um site de compras na visão do usuário final, doravante 
  chamado de comprador.
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
* [Relatório de testes](../reports/Cucumber Reports.html) - gerado dinameicamente após a execução dos testes utilizando o [Cucumber reports service](https://reports.cucumber.io/), porém o link leva a um exemplo.

## Recursos

### Ferramentas e técnicas
* Selenium webdriver
* [ChromeDriver 87.0.4280.88](https://chromedriver.storage.googleapis.com/index.html?path=87.0.4280.88/)
* Page objects (selenium)
* Page factory (selenium)

### Ambiente
* OS Name	Microsoft Windows 10 Enterprise
* Processor	Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz, 2808 Mhz, 2 Core(s), 4 Logical 
  Processor(s)