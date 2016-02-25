package etaxi.entity;

/**
 * Класс для хранения данных такси
 */
public class Taxi {

    // Идентификатор такси
    private Long taxiId;
    // Имя, Фамилия
    private String name;
    // Телефон
    private String phone;
    // статус (не работает, свободен, занят)
    private byte taxiStatus;
    // местоположение десятичные градусы (вида 56.9613438,24.1900393)
    private String location;
    // машины, рег.номер, бортовой номер
    private String car;
    // логин
    private String login;
    // пароль
    private String password;
    // рейтинг по среднему значению отзывов
    private double rating;



}
