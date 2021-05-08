@echo off
@if not "%ECHO%" == "" echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

set current_path=.\
if "%OS%" == "Windows_NT" set current_path=%~dp0%
echo %current_path%

@rem 运行时参数
set BASE_DIR=%current_path%..
set CONF_DIR=%BASE_DIR%\conf
set CLASSPATH=.;%BASE_DIR%\conf;%BASE_DIR%\libs\*
set LOG_DIR=%BASE_DIR%

set OPTS_APP=-Dlogback.configurationFile=%CONF_DIR%\logback.xml -Dlog.dir=%LOG_DIR%
set OPTS_JAVA_EXT=-Djava.net.preferIPv4Stack=true -Dapplication.codeset=UTF-8 -Dfile.encoding=UTF-8
set OPTS_JAVA=%OPTS_JAVA_EXT% %OPTS_APP%

java %OPTS_JAVA% -classpath "%CLASSPATH%" org.chench.extra.ffmpeg.gui.Bootstrap

@pause

