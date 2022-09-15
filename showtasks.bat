call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo RUNCRUD has errors – breaking work
goto fail

:openBrowser
start http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto finish
echo.
echo We can not open the link
goto fail

:finish
echo.
echo This is the end.

:fail
echo.
echo There were errors