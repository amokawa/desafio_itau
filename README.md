# Desafio Itaú

Contém plano de testes, relatórios de testes e automação

## Planos de teste

* Web ([WEB_TEST_PLAN.md](src/test/resources/platform/web/WEB_TEST_PLAN.md))
* API ([API_TEST_PLAN.md](src/test/resources/platform/api/API_TEST_PLAN.md))
* Mobile ([MOBILE_TEST_PLAN.md](src/test/resources/platform/mobile/MOBILE_TEST_PLAN.md))

### Resultados dos testes

Por utilizar a versão mais recente do Cucumber, podemos desfrutar do serviço
do [Cucumber reports](https://reports.cucumber.io/). Mas uma cópia destes relatórios gerados podem ser encontrados em
[src/test/resources/report_sample](src/test/resources/report_sample) ou acesando o
[link do Cucumber reports collection](https://reports.cucumber.io/report-collections/b216cdd2-25d5-4b38-a389-36403f5f4cee)
.

## Pré-requisitos

* Instalar o [ChromeDriver 87.0.4280.88](https://chromedriver.storage.googleapis.com/index.html?path=87.0.4280.88/)
* Instalar o [Apache Jmeter 5.2.1](https://jmeter.apache.org/download_jmeter.cgi)
* Instalar o [Appium Desktop v1.15.1](http://appium.io/docs/en/about-appium/getting-started/)

## Executando os testes

* Caso esteja em uma máquina Windows basta executar:
    * `./bin/test_api`
        * Note que o link do relatório dos testes do Cucumber será exibido na saída do console, como na imagem a seguir.
          <details>
            <summary>Clique para ver o exemplo</summary>

          ![Cucumber reports output example](assets/cucumber_reports.png)
          </details>
    * `./bin/test_mobile` - Importante: requer um dispositivo/emulador conectado.
        * Note que o link do relatório dos testes do Cucumber será exibido na saída do console, como na imagem a seguir.
          <details>
            <summary>Clique para ver o exemplo</summary>

          ![Cucumber reports output example](assets/cucumber_reports.png)
          </details>
    * `./bin/test_web`
        * Note que o link do relatório dos testes do Cucumber será exibido na saída do console, como na imagem a seguir.
          <details>
            <summary>Clique para ver o exemplo</summary>

          ![Cucumber reports output example](assets/cucumber_reports.png)
          </details>
    * `./bin/test` - caso queira executar todos os testes e ainda ter as saídas salvas em `out\out_<plataforma>.txt`.

* Caso esteja em outro sistema operacional:
    * Adicionar a variável de ambiente: `CUCUMBER_PUBLISH_ENABLED=true`
    * Executar: `mvn clean test`