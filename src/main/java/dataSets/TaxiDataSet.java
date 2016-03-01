package dataSets;

/** Проект etaxi
 * Класс для хранения данных такси
 */
public class TaxiDataSet {

    private Long taxiId;     // Идентификатор такси
    private String name;     // Имя, Фамилия
    private String phone;    // Телефон
    private byte taxiStatus; // статус (не работает, свободен, занят)
    private String location; // местоположение десятичные градусы (вида 56.9613438,24.1900393)
    private String car;      // машины, рег.номер, бортовой номер
    private String login;    // логин
    private String password; // пароль
    private double rating;   // рейтинг по среднему значению отзывов

    public TaxiDataSet(Long taxiId, String name, String phone, String login, String password) {
        this.taxiId = taxiId;
        this.name = name;
        this.phone = phone;
        this.login = login;
        this.password = password;
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
        return "TaxiDataSet{" +
                "taxiId=" + taxiId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", taxiStatus=" + taxiStatus +
                ", location='" + location + '\'' +
                ", car='" + car + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                '}';
    }
}

