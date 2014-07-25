package springProject.domain;

import javax.persistence.*;

/**
 * Created by Юрий on 10.07.2014.
 */
@Entity
@Table(name = "DB_USER")
public class User {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "SEQ_USER_ID", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")

    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_LOG")
    private String login;

    @Column(name = "USER_PAS")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return  "User:    " +
                "\n\tid = " + id +
                "\n\tlogin = " + login +
                "\n\tpassword = " + password;
    }
}
