@ ECHO off
CD msg
for %%i in (*.proto) do (
echo %%i
..\protoc ./%%i --java_out=..\..\..\..\..\..\game-gen\src\main\java\
)
echo end
pause