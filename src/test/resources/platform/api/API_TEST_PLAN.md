# Plano de teste de API

## Escopo
* Serão realizados testes de API para os seguintes endpoints:
    * CRUD no endpoint `/employees`
        * http://dummy.restapiexample.com/platform.api/v1/employees
        * http://dummy.restapiexample.com/platform.api/v1/employee/{id}
        * http://dummy.restapiexample.com/platform.api/v1/create
        * http://dummy.restapiexample.com/platform.api/v1/update/{id}
        * http://dummy.restapiexample.com/platform.api/v1/delete/{id}
    * Verificação do retorno do endpoint `/photos`
        * https://jsonplaceholder.typicode.com/photos
* Para os testes de performance será utilizado a ferramenta chamada Jmeter e será elaborado um 
  plano de teste a parte.
* Testes não funcionais, além dos testes mencionados neste plano de teste estão fora de escopo.
    

## Artefatos
* Plano de teste (este documento)
* Casos de teste
  * [/employees](api_employees.feature)
  * [/photos](api_photos.feature)
* Bugs e melhorias
* [Relatório de testes](https://reports.cucumber.io/report-collections/479d5ff5-57b9-4661-8b13-c2d04424bf1b) - gerado
  dinamicamente após a execução dos testes utilizando o [Cucumber reports service](https://reports.cucumber.io/)

## Recursos

### Ferramentas e técnicas
* Rest-assured (4.3.3)
* Cucumber (6.8.1)

### Ambiente
* OS Name	Microsoft Windows 10 Enterprise
* Processor	Intel(R) Core(TM) i7-6600U CPU @ 2.60GHz, 2808 Mhz, 2 Core(s), 4 Logical
  Processor(s)
