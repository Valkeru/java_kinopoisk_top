Требования для запуска:
* Docker
* Docker Compose

Запуск:
0. ./gradlew war
1. cd docker
2. docker-compose build
3. docker-compose up -d

При первом запуске выполняется импорт дампа в MySQL (содержит данные за 25-30 сентября 2019 года)

Доступ к приложению: http://localhost:8005
nginx-config/kinotop.conf - конфиг для Nginx на хосте.
1. Создать симлинк на конфиг в `/etc/nginx/sites-enabled/`
2. `# echo '127.0.0.1       kinotop.local' >> /etc/hosts`
3. `systemctl restart nginx`

После перезапуска приложение доступно по адресу kinotop.local

Для получения топа за текущий день выполнить в контейнере команду:
```bash
java -classpath '/usr/local/tomcat/webapps/GradleSpring/WEB-INF/lib/*:/usr/local/tomcat/webapps/GradleSpring/WEB-INF/classes' ru.valkeru.kinotop.command.Main
```
