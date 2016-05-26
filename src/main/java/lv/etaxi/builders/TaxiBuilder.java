package lv.etaxi.builders;

import lv.etaxi.entity.Taxi;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */
public class TaxiBuilder {

    public static final Long   DEFAULT_ID = (long) 0;
    public static final String DEFAULT_NAME = "Fernando Alonso Díaz";
    public static final String DEFAULT_CAR = "Ford Mondeo 1.8L";
    public static final String DEFAULT_PHONE = "(+371) 000000000";
    public static final String DEFAULT_LOGIN = "login";
    public static final String DEFAULT_PASSWORD = "password";

    private Long id = DEFAULT_ID;
    private String login = DEFAULT_LOGIN;
    private String car = DEFAULT_CAR;
    private String password = DEFAULT_PASSWORD;
    private String phone = DEFAULT_PHONE;
    private String name = DEFAULT_NAME;

    private TaxiBuilder() {}

    public static TaxiBuilder aTaxi() {
        return new TaxiBuilder();
    }

    public TaxiBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TaxiBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TaxiBuilder withCar(String car) {
        this.car = car;
        return this;
    }

    public TaxiBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public TaxiBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public TaxiBuilder withNoLogin() {
        this.login = null;
        return this;
    }

    public TaxiBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TaxiBuilder withNoPassword() {
        this.password = null;
        return this;
    }


    public TaxiBuilder but() {
        return TaxiBuilder
                .aTaxi()
                .withName(name)
                .withCar(car)
                .withPhone(phone)
                .withPassword(password)
                .withLogin(login);
    }

    public Taxi build() {
        return new Taxi(id, name, car, phone, login, password);
    }
}