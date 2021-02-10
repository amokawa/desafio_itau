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
    * `./bin/test_perf` - executa os testes com o Apache JMeter.

* Caso o sistema operacional seja Linux ou OS X:
    * Adicionar a variável de ambiente: `CUCUMBER_PUBLISH_ENABLED=true`
    * Executar: `CUCUMBER_PUBLISH_ENABLED=true mvn clean test` - para todos os testes de web, api e mobile
    * Executar: `CUCUMBER_PUBLISH_ENABLED=true CUCUMBER_FILTER_TAGS="@api" mvn clean test` - somente os testes de API
    * Executar: `CUCUMBER_PUBLISH_ENABLED=true CUCUMBER_FILTER_TAGS="@web" mvn clean test` - somente os testes de WEB
    * Executar: `CUCUMBER_PUBLISH_ENABLED=true CUCUMBER_FILTER_TAGS="@mobile" mvn clean test` - somente os testes de
      MOBILE
    * Para os testes de performance:
    ```bash
    # Garanta que os arquivos .jtl e os diretórios perf_reports/web e perf_reports/api 
    # sejam removidos antes de cada execução.
    jmeter -n -t scripts/desafio_itau_web.jmx -l perf_reports/jmeter.jtl && \
    jmeter -g perf_reports/jmeter.jtl -o perf_reports/web && \
    jmeter -n -t scripts/desafio_itau_api.jmx -l perf_reports/jmeter1.jtl && \
    jmeter -g perf_reports/jmeter1.jtl -o perf_reports/api
    ```

## Testes de performance

Os testes de performance são executados em background, ou seja, sem interface gráfica. Os cenários e os comentários
sobre as plataformas testadas são encontrada
em [perf_reports/PERF_REPORT_ANALYSIS.md](perf_reports/PERF_REPORT_ANALYSIS.md). Os dashboards com métricas coletadas de
cada teste são gerados dinamicamente e criados em formato HTML.

* [perf_reports/api/index.html](perf_reports/api/index.html)
* [perf_reports/web/index.html](perf_reports/web/index.html)