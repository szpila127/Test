call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD has errors - braking work
goto fail

:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open Chrome and show tasks - breaking work

:end
echo.
echo Work is finished.

:fail
echo.
echo There were errors