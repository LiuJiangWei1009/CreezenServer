cd business
call mvn package
cd ..
rd /s /q resource
rd resource.tar.gz
xcopy /E /I  .\profile\nginx\conf .\resource\nginx\conf
xcopy /E /I  .\profile\nginx\web .\resource\nginx\web
xcopy /E /I  .\profile\nginx\templetes .\resource\nginx\templetes
xcopy /E /I  .\profile\nginx\file .\resource\nginx\file
mkdir ./resource\nginx\log
xcopy /E /I  .\profile\mysql\conf .\resource\mysql\conf
xcopy /E /I  .\profile\mysql\init .\resource\mysql\init
mkdir .\resource\mysql\data
mkdir .\resource\mysql\uploads
xcopy /E /I  .\profile\redis\conf .\resource\redis\conf
mkdir .\resource\redis\data
xcopy .\profile\tomcat\apps\ROOT.war .\resource\tomcat\apps\ /Y
xcopy /E /I  .\profile\tomcat\config .\resource\tomcat\config
mkdir .\resource\tomcat\log
xcopy .\profile\docker-compose.yml .\resource\ /Y

cd profile
docker compose down
cd ..
tar -czf resource.tar.gz resource
scp resource.tar.gz root@117.72.197.40:/