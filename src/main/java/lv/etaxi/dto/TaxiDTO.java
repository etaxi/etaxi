package lv.etaxi.dto;


/** Проект etaxi
 * Класс DTO для передачи данных по такси
 */
public class TaxiDTO {

    private Long taxiId;   // Идентификатор

    private String name;   // Имя, Фамилия

    private String phone;   // Телефон

    private Byte  taxiStatus;   // статус (не работает, свободен, занят)

    private String location;    // местоположение десятичные градусы (вида 56.9613438,24.1900393)

    private String car;         // машины, рег.номер, бортовой номер

    private String login;       // логин

    private String password;    // пароль

    private Double rating;       // рейтинг по среднему значению отзывов


    public TaxiDTO(Long taxiId, String name, String car, String phone, String login, String password) {
        this.taxiId = taxiId;
        this.name = name;
        this.phone = phone;
        this.taxiStatus = 1;
        this.location = "56.9613438,24.1900393";
        this.car = car;
        this.login = login;
        this.password = password;
        this.rating = 0.0;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Byte getTaxiStatus() {
        return taxiStatus;
    }

    public String getLocation() {
        return location;
    }

    public String getCar() {
        return car;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Double getRating() {
        return rating;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTaxiStatus(Byte taxiStatus) {
        this.taxiStatus = taxiStatus;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "TaxiDTO{" +
                "taxiId=" + taxiId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", taxiStatus=" + taxiStatus +
                ", location='" + location + '\'' +
                ", car='" + car + '\'' +
                ", login='" + login + '\'' +
                ", rating=" + rating +
                '}';
    }
}

