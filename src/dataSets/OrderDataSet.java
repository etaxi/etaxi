package dataSets;

/** Проект etaxi
 * Класс для хранения данных заказа
 */
public class OrderDataSet {

    // Идентификатор заказа
    private Long orderId;
    // Клиент
    private Long custumerId;
    // Дата Время "2015-02-18T00:00:00"
    private String dateTime;
    // статус (ждёт, едет, выполнен)
    private byte orderStatus;
    // откуда строка
    private String FromString;
    // откуда местоположение десятичные градусы (вида 56.9613438,24.1900393)
    private String FromLocation;
    // куда стока
    private String ToString;
    // куда местоположение десятичные градусы (вида 56.9613438,24.1900393)
    private String ToLocation;
    // Такси
    private Long taxiId;
    // растояние
    private double distance;
    // стоимость
    private double price;
    // rating  [1..10]
    private byte rate;
    // отзыв
    private String feedback;

}


