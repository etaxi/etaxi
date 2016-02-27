package dataSets;

/** Проект etaxi
 * Класс для хранения данных клиента
 */
public class CustomerDataSet {

    // Идентификатор клиента
    private Long customerId;
    // Имя, Фамилия
    private String name;
    // Телефон
    private String phone;
    // логин
    private String login;
    // пароль
    private String password;

    public CustomerDataSet(Long customerId, String name, String phone, String login, String password) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.login = login;
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


}


