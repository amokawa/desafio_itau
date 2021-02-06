@ECHO OFF

REM Deletes the reports folder and all its contents
RMDIR /S /Q perf_reports
DEL /F /Q assets\jmeter*
MKDIR perf_reports\web
MKDIR perf_reports\api

REM Runs the jmeter script (JMX) and generates the JTL file and generates the Jmeter reports dashboard
jmeter -n -t scripts/desafio_itau_web.jmx -l assets/jmeter.jtl && jmeter -g assets/jmeter.jtl -o perf_reports/web && ^
jmeter -n -t scripts/desafio_itau_api.jmx -l assets/jmeter1.jtl && jmeter -g assets/jmeter1.jtl -o perf_reports/api