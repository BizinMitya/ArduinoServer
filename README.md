# ArduinoServer
Сервер для клиента на Arduino.

Обрабатывает два вида запросов: на запись события в файл и на просмотр событий из файла.

Сервер работает на порту 8080. Логи уровней DEBUG и выше пишутся в стандартный поток вывода.

Файл с логами событий (ArduinoServerLogFile.txt) создаётся в домашнем каталоге пользователя в папке .ArduinoServerLogs.

Для сборки проекта из исходников выполнить maven goal: mvn clean compile assembly:single

Запуск jar производить с параметрами: java -jar -Dfile.encoding=UTF-8 ArduinoServer-1.0-SNAPSHOT.jar (чтобы не слетела кодировка).

**Запись в файл**

Для отправки события на запись в файл, нужно послать GET запрос на ресурс /events?event=[событие]

**Чтение событий из файла**

Для просмотра событий из файла, нужно послать GET запрос на ресурс /logs.
Логи будут выведены в формате HTML.

**Формат файла**

События в файле пишутся построчно в формате [date],[event]
