@ECHO OFF

RMDIR /S /Q %CD%\out\
MKDIR %CD%\out\
call %CD%\bin\test_api.bat > %CD%\out\out_api.txt 2>&1 & ^
call %CD%\bin\test_mobile.bat > %CD%\out\out_mobile.txt 2>&1 & ^
call %CD%\bin\test_web.bat > %CD%\out\out_web.txt 2>&1
