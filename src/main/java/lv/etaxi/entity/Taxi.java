package lv.etaxi.entity;

import javax.persistence.*;

@Entity
@Table(name = "taxis")
public class Taxi {

    // Идентификатор такси
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "Id", columnDefinition = "int(11)")
    @Column(name = "Id")
    private Long taxiId;

    // Имя, Фамилия
    //@Column(name = "name", columnDefinition = "char(100)" )
    @Column(name = "name", unique = false, updatable = true)
    private String name;

    // Телефон
    //@Column(name = "phone", columnDefinition = "char(50)")
    @Column(name = "phone", unique = true, updatable = true)
    private String phone;

    // статус (не работает, свободен, занят)
    //@Column(name = "taxiStatus", columnDefinition = "int(1)")
    @Column(name = "taxiStatus", unique = false, updatable = true)
    private byte   taxiStatus;

    // местоположение десятичные градусы (вида 56.9613438,24.1900393)
    //@Column(name = "location", columnDefinition = "char(50)")
    @Column(name = "location", unique = false, updatable = true)
    private String location;

    // машины, рег.номер, бортовой номер
    //@Column(name = "car", columnDefinition = "char(100)")
    @Column(name = "car", unique = false, updatable = true)
    private String car;

    // логин
    //@Column(name = "login", columnDefinition = "char(50)" )
    @Column(name = "login", unique = true, updatable = true)
    private String login;

    // пароль
    //@Column(name = "password", columnDefinition = "char(50)" )
    @Column(name = "password", unique = false, updatable = true)
    private String password;

    // рейтинг по среднему значению отзывов
    //@Column(name = "rating", columnDefinition = "float(8,2)")
    @Column(name = "rating", unique = false, updatable = true)
    private double rating;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public Taxi() {}

    public Taxi(Long taxiId, String name, String car, String phone, String login, String password) {
        this.taxiId = taxiId;
        this.name = name;
        this.phone = phone;
        this.taxiStatus = 1;
        this.location = "56.9613438,24.1900393";
        this.car = car;
        this.login = login;
        this.password = password;
        this.rating = 0;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getTaxiStatus() {
        return taxiStatus;
    }

    public void setTaxiStatus(byte taxiStatus) {
        this.taxiStatus = taxiStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
                "\"taxiId\":" + "\"" + taxiId + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"phone\":" + "\"" + phone + "\"" +
                ", \"taxiStatus\":" + "\"" + taxiStatus + "\"" +
                ", \"location\":" + "\"" + location + "\"" +
                ", \"car\":" + "\"" + car + "\"" +
                ", \"login\":" + "\"" + login + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                ", \"rating\":" + "\"" + rating + "\"" +
                '}';
    }
}

