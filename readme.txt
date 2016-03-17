/** Проект etaxi

ФУНКЦИОНАЛ

1. такси:
    1.0. может взять только один заказ
    1.1. вход по паролю
    1.2. смотреть список открытых заказов
    1.3. взять заказ


2. клиент:
    2.0. у клиента может быть только один открытый заказ
    2.1. вход с паролем
    2.2. создать заказ указав : откуда, куда, когда
    2.3. отменить заказ
    2.4. оставить оценку и отзыв


ТАБЛИЦЫ БАЗЫ ДАННЫХ  (возможно стоит некоторые данные вынести в отдельные таблицы)

1. taxi
    -taxiId     [long(9)]  ключ записи
    -name       [text]     имя, фамилия
    -phone      [text]     телефон
    -taxiStatus [int(1)]   статус (не работает, доступен, занят)
    -location   [text]     местоположение - десятичные градусы (вида 56.9613438,24.1900393)
    -car        [text]     машина, гос.номер, бортовой номер
    -login      [text]
    -password   [text]
    -rating     [double]   среднее значение по оценкам клиентов


2. customers
    -customerId [long(9)]   ключ записи
    -name       [text]      имя, фамилия
    -phone      [text]      номер телефона (обязательное поле для заполнения)
    -login      [text]
    -password   [text]


3. orders
    -orderId    [long(9)]   ключ записи
    -customerId [long(9)]   ключ записи клиента
    -datetime   [datetime]  дата и время заказа
    -orderStatus[text]      статус (в ожидани, в пути, заказ выполнен)
    -fromAdress [text]      откуда - адрес (строка)
    -toAdress   [text]      куда - адрес (строка)
    -taxiId     [long(9)]   ключ записи такси
    -distance   [double]    расстояние по факту (для статуса "выполнен")
    -price      [double]    стоимость по факту  (для статуса "выполнен")
    -rate       [int(2)]    оценка от 1 до 10   (для статуса "выполнен")
    -feedback   [text]      текст отзыва (для статуса "выполнен")


ПОЗЖЕ НЕОБХОДИМЫЙ ФУНКЦИОНАЛ:
1. регистраця для такси и клиентов
 1.1. хранение в базе безопасного пароля НЕ в открытом виде   типо  http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 1.2. отправка на телефон или майл  кода для подтверждения регистриции

2. расчёт растояния по gooogle map Api

3. показывать карту где такси

4. надо спросить у препода как лучше хранить статус (не работает, доступен, занят), учитывая что дальше будет Hibernate
    варианты:
        -integer  и соответвие в коде
        -строка с текстом статуса
        -тип данных enum в MySQl







Материалы:

1. DAO           https://stepic.org/lesson/JDBC-12404/step/1?unit=2834

2. Servlets      https://github.com/vitaly-chibrikov/stepic_java_webserver

3. Autorization  https://stepic.org/course/146/syllabus?module=2
                 https://github.com/vitaly-chibrikov/stepic_java_webserver/tree/master/L2.1%20Authorization

4. Bootstrap    http://bootstrap-3.ru/
                http://www.w3schools.com/bootstrap/bootstrap_templates.asp
                + можно почитать про "блочную вёрстку"  http://www.websovet.com/blochnaya-verstka-urok-1

5. password     http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

6. Google Maps   https://habrahabr.ru/post/148986/