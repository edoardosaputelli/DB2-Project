package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "user", schema = "db2_project_schema")
@NamedQuery(name = "UserEntity.checkLogin", query = "SELECT r FROM UserEntity r  WHERE r.userName = ?1 and r.userPassword = ?2")
public class UserEntity implements Serializable {

    private int idUser;
    private String userName;
    private String userPassword;
    private String email;
    private int points;
    private byte flagStatus;
    private Timestamp dateLastLogin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userPassword", nullable = false, length = 45)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "points", nullable = false)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Basic
    @Column(name = "flagStatus", nullable = false)
    public byte getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(byte flagStatus) {
        this.flagStatus = flagStatus;
    }

    @Basic
    @Column(name = "dateLastLogin", nullable = false)
    public Timestamp getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Timestamp dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (points != that.points) return false;
        if (flagStatus != that.flagStatus) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (dateLastLogin != null ? !dateLastLogin.equals(that.dateLastLogin) : that.dateLastLogin != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + (int) flagStatus;
        result = 31 * result + (dateLastLogin != null ? dateLastLogin.hashCode() : 0);
        return result;
    }
}
