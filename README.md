Требования для запуска:
* Docker
* Docker Compose

Запуск:
1. ./gradlew war
2. cd docker
3. docker-compose build
4. docker-compose up -d

При первом запуске выполняется импорт дампа в MySQL (содержит данные за 25-30 сентября 2019 года)

Доступ к приложению: http://localhost:8005
nginx-config/kinotop.conf - конфиг для Nginx на хосте.
1. Создать симлинк на конфиг в `/etc/nginx/sites-enabled/`
2. `# echo '127.0.0.1       kinotop.local' >> /etc/hosts`
3. `# systemctl restart nginx`

После перезапуска Nginx приложение доступно по адресу kinotop.local

Для получения топа за текущий день выполнить в контейнере команду:
```bash
java -classpath '/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/*:/usr/local/tomcat/webapps/ROOT/WEB-INF/classes' ru.valkeru.kinotop.command.Parser
```
Для сбора данных за определённый день может быть задан аргумент --date, значение — дата в формате `yyyy-MM-dd`
