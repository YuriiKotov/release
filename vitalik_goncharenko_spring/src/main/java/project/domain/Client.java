package project.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yurii on 7/24/14.
 */
@Entity
@Table(name = "Taxi_Client")
public class Client {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Taxi_Client_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")

    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "MOBILE")
    private Integer mobile;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SPENT")
    private Integer spent;

    @Column(name = "LAST_ORDER")
    private Date last_order;

    @Column(name = "SUPER_USER")
    private boolean super_user;

    public Client() {
    }

    public Client(String login, String password, String first_name, String last_name, Integer mobile, String address, Integer spent, Date last_order, boolean super_user) {
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile = mobile;
        this.address = address;
        this.spent = spent;
        this.last_order = last_order;
        this.super_user = super_user;
    }

    public boolean isSuper_user() {
        return super_user;
    }

    public void setSuper_user(boolean super_user) {
        this.super_user = super_user;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSpent() {
        return spent;
    }

    public void setSpent(Integer spent) {
        this.spent = spent;
    }

    public Date getLast_order() {
        return last_order;
    }

    public void setLast_order(Date last_order) {
        this.last_order = last_order;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mobile=" + mobile +
                ", address='" + address + '\'' +
                ", spent=" + spent +
                ", last_order=" + last_order +
                ", super_user=" + super_user +
                '}';
    }
}
