/** Проект etaxi

ФУНКЦИОНАЛ

Customer
1. Регистрация
2. Авторизация
3. Редактировать свои данные
4. Добавить заказ (откуда, куда, когда) может быть несколько открытых заказав
5. Отменить открытый заказ, если его ещё не взял таксист (удаление)
6. Редактировать открытый заказ, если его ещё не взял таксист
7. Просмотреть историю своих заказов
8. Оставить оценку и комментарий к выполненному заказу

Taxi
1. Регистрация
2. Авторизация
3. Редактировать свои данные
4. Просмотреть все открытые заказы
5. Взять один из открытых (меняет статус водителя на "занят")
6. Просмотреть историю своих выполненные заказов
7. Выполнел взяты заказ (меняет статус водителя на "свободен")
8. Отмена взятого заказа

Administrator
1. Регистрация
2. Авторизация
3. Редактировать свои данные
4. Редактировать любого Customer
5. Редактировать любого Taxi
6. Внести заказ на с указанием любого ID Customer
7. Отменить любой заказ
8. Редактировать любой заказ



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
    -datetime   [datetime]  дата и время заказа ввода/последнего изменения заказа
    -ordereddatetime   [datetime]  дата и время, на которое сделан заказ такси
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

7. Intellij IDEA деплой на Tomcat  http://devcolibri.com/4249

8. JSP  https://www3.ntu.edu.sg/home/ehchua/programming/java/JSPByExample.html

9. GitHub Merging, Deleting, and Comparing Branches   https://www.jetbrains.com/help/phpstorm/2016.1/merging-deleting-and-comparing-branches.html?origin=old_help

10. HTTP     15 тривиальных фактов о правильной работе с протоколом HTTP     https://habrahabr.ru/company/yandex/blog/265569/

11. Spring MVC     http://devcolibri.com/3669
                   http://mai.pmoproject.ru/pages/viewpage.action?pageId=4424007

12. JSP Tutorial   http://www.tutorialspoint.com/jsp/index.htm

14. Spring 3 MVC + Spring Security + Hibernate    https://habrahabr.ru/post/111102/

15. REST сервис   http://devcolibri.com/3732

16. DTO   http://design-pattern.ru/patterns/data-transfer-object.html

17. Spring IoC   http://spring-projects.ru/guides/lessons/lesson-2/

18. Виртуальная машина Java   http://ermak.cs.nstu.ru/trans/Trans472.htm

19. GenericDAO    http://stackoverflow.com/questions/9954761/dao-design-pattern

20. SQL Foren Key   http://denis.in.ua/foreign-keys-in-mysql.htm

21. создание одностраничных веб-приложений  (Spring MVC и AngularJs)  https://habrahabr.ru/company/piter/blog/269217/

22. сравнение Angular и React   https://habrahabr.ru/post/274523/

23. Spring Boot   + REST     https://spring.io/guides/gs/rest-service/

24. Структура приложений   http://javatalks.ru/topics/16739?page=1#80068
