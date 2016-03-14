package dataSets;

/** Проект etaxi
 * Класс для хранения данных клиента
 */
public class CustomerDataSet {

    private Long customerId;    // Идентификатор клиента
    private String name;        // Имя, Фамилия
    private String phone;       // Телефон
    private String login;       // логин
    private String password;    // пароль
    private String tariff;

    public CustomerDataSet(Long customerId, String name, String phone, String login, String password, String tariff) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.tariff = tariff;
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

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "{" +
                "\"customerId\":" + "\"" + customerId + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"phone\":" + "\"" + phone + "\"" +
                ", \"login\":" + "\"" + login + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                ", \"tariff\":" + "\"" + tariff + "\"" +
                '}';
    }


}

