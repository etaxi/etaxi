package lv.etaxi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/** Проект etaxi
 * Класс для хранения данных клиента
 */
@Entity
@Table(name = "customers")

public class Customer implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;    // Идентификатор клиента

    @Column(name = "name", unique = false, updatable = true)
    private String name;        // Имя, Фамилия

    @Column(name = "phone", unique = true, updatable = true)
    private String phone;       // Телефон (он же логин)

    @Column(name = "password", unique = false, updatable = true)
    private String password;    // пароль

    //Не используем в функционале, добавлен только для пробы связей Hibernate
    @OneToMany(mappedBy="customerId", fetch=FetchType.EAGER)
    private List<Order> listOfOrders;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public Customer() {}

    public Customer(Long customerId, String name, String phone, String password) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"customerId\":" + "\"" + customerId + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"phone\":" + "\"" + phone + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                '}';
    }

}

