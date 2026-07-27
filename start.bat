@echo off
set /p "mysqlPass=MySQL password:"
set /p "startPos=Start location:"
set /p "finalPos=Final location:"
set /p "binOrigin=Start bin file:"
set /p "binFinal=Final bin file:"

set BASE_PATH=C:\ProgramData\MySQL\MySQL Server 8.0\Data\DESKTOP-04KARUN-bin.

mysqldump -u root -p%mysqlPass% -P 3307 --source-data=2 --databases tianji | findstr /V /C:"-- Dump completed on" > profile-c/mysql/init/creezen.sql

mysqlbinlog --no-defaults --base64-output=decode-rows --skip-gtids --start-position=%startPos% --stop-position=%finalPos% --database=tianji  "%BASE_PATH%%binOrigin%" "%BASE_PATH%%binFinal%" > raw.txt

findstr /V /C:"#" /C:"SET " /C:"/*!"  /C:"COMMIT" /C:"BEGIN" /C:"DELIMITER" raw.txt > temp.txt

powershell -Command "$content=Get-Content temp.txt -Raw -Encoding UTF8; $content=$content -replace ',\r?\n', ',' -replace ',\s+', ',';  Set-Content temp.sql $content -Encoding UTF8"

powershell -Command "$content; Get-Content temp.sql -Encoding UTF8 | ForEach-Object { $line = $_ ; $content += $line + ';' +  [Environment]::NewLine }; $content=$content -replace ';\r\n;\r\n+',';';  Set-Content profile/mysql/migration/V2__migration.sql $content -Encoding UTF8"

del raw.txt

del temp.sql

del temp.txt

winmergeu /r  profile profile-c  /f "creezen" -cfg Settings/DirViewExpandSubdirs=1

choice /c YN /m "是否发布？"
if errorlevel 2 (
    exit /b
)
deploy.bat