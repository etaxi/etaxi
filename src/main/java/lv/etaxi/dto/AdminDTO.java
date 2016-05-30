package lv.etaxi.dto;

/**
 * Created by Genady Zalesky on 30.05.2016
 */
public class AdminDTO {

    private Long adminId;   // Идентификатор
    private String name;   // Имя, Фамилия
    private String login;       // логин
    private String password;    // пароль

    public AdminDTO(Long adminId, String name, String login, String password) {

        this.adminId = adminId;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getAdminId() {

        return adminId;
    }

    public String getName() {

        return name;
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {

        return "AdminDTO{" +
                "\"adminId\":" + "\"" + adminId + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"login\":" + "\"" + login + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                '}';
    }
}
