package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user", schema = "db2_project_schema")
@NamedQueries({@NamedQuery(name = "UserEntity.checkLogin", query = "SELECT r FROM UserEntity r  WHERE r.userName = ?1 and r.userPassword = ?2"),
@NamedQuery(name = "UserEntity.getLeaderboard",
        query = "SELECT i FROM UserEntity i JOIN QuestionnaireResponseEntity r JOIN QuestionnaireEntity q JOIN ProductEntity p " +
                "WHERE i = r.user AND r.questionnaire = q AND q.productoftheday = p " +
                "AND r.flagCancelled = false and p.date = :today ORDER BY i.points")})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "userName", nullable = false, length = 45)
    private String userName;
    @Basic
    @Column(name = "userPassword", nullable = false, length = 45)
    private String userPassword;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "points", nullable = false)
    private int points;
    @Basic
    @Column(name = "flagStatus", nullable = false)
    private byte flagStatus;
    @Basic
    @Column(name = "dateLastLogin", nullable = false)
    private Timestamp dateLastLogin;

    public UserEntity(){}

    public UserEntity(String userName, String userPassword, String email) {
        //this.idUser = getIdUser();
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.points = 0;
        this.flagStatus = 0;
        Date date = new Date();
        this.dateLastLogin = new Timestamp(date.getTime());
    }


    @OneToMany(mappedBy = "user")
    private List<QuestionnaireResponseEntity> qRlist;



    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public byte getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(byte flagStatus) {
        this.flagStatus = flagStatus;
    }


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
