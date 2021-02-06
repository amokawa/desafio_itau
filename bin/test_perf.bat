@ECHO OFF

REM Deletes the reports folder and all its contents
RMDIR /S /Q %CD%\perf_reports\api
RMDIR /S /Q %CD%\perf_reports\web
MKDIR %CD%\perf_reports\api
MKDIR %CD%\perf_reports\web
DEL /F /Q %CD%\perf_reports\*.jtl

REM Runs the jmeter script (JMX) and generates the JTL file and generates the Jmeter reports dashboard
jmeter -n -t scripts/desafio_itau_web.jmx -l perf_reports/jmeter.jtl && ^
jmeter -g perf_reports/jmeter.jtl -o perf_reports/web && ^
jmeter -n -t scripts/desafio_itau_api.jmx -l perf_reports/jmeter1.jtl && ^
jmeter -g perf_reports/jmeter1.jtl -o perf_reports/api
