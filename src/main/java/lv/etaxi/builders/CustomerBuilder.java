package lv.etaxi.builders;

import lv.etaxi.entity.Customer;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

public class CustomerBuilder {

    public static final Long   DEFAULT_ID = (long) 0;
    public static final String DEFAULT_NAME = "Oleg Ivanov";
    public static final String DEFAULT_PHONE = "(+371) 26907856";
    public static final String DEFAULT_PASSWORD = "password";

    private Long id = DEFAULT_ID;
    private String password = DEFAULT_PASSWORD;
    private String phone = DEFAULT_PHONE;
    private String name = DEFAULT_NAME;

    private CustomerBuilder() {}

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder withNoPassword() {
        this.password = null;
        return this;
    }

    public CustomerBuilder but() {
        return CustomerBuilder
                .aCustomer()
                .withName(name)
                .withPhone(phone)
                .withPassword(password);
    }

    public Customer build() {
        return new Customer(id, name, phone, password);
    }
}