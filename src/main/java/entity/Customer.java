package entity;

/** Проект etaxi
 * Класс для хранения данных клиента
 */
public class Customer {

    private Long customerId;    // Идентификатор клиента
    private String name;        // Имя, Фамилия
    private String phone;       // Телефон (он же логин)
    private String password;    // пароль

    public Customer(Long customerId, String name, String phone, String password) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.password = password;
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

