package lv.etaxi.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admins")
public class Admin implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;    // Идентификатор

    @Column(name = "name", unique = false, updatable = true)
    private String name;        // Имя, Фамилия

    @Column(name = "login", unique = true, updatable = true)
    private String login;       //логин

    @Column(name = "password", unique = false, updatable = true)
    private String password;    // пароль

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public Admin() {}

       public Admin(Long adminId, String name, String login, String password){

        this.adminId = adminId;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getAdminId() {

        return adminId;
    }

    public void setAdminId(Long adminId) {

        this.adminId = adminId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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

    @Override
    public String toString() {

        return "{" +
                "\"adminId\":" + "\"" + adminId + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"login\":" + "\"" + login + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                '}';
    }
}
