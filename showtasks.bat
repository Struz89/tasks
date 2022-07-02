call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runwebsite
echo.
echo runcrud.bat has errors – breaking work
goto fail

:runwebsite
start chrome.exe http://localhost:8080/crud/v1/task/tasks
goto end

:fail
echo.
echo There were errors!

:end
echo.
echo Showtasks.bat is finished.