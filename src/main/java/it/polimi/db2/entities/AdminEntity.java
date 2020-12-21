package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin", schema = "db2_project_schema")
@NamedQueries({@NamedQuery(name = "AdminEntity.checkLogin", query = "SELECT r FROM AdminEntity r  WHERE r.adminName = ?1 and r.adminPassword = ?2")
})

public class AdminEntity implements Serializable {
    private String adminName;
    private String adminPassword;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminName", nullable = false, length = 45)
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "adminPassword", nullable = false, length = 45)
    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (adminName != null ? !adminName.equals(that.adminName) : that.adminName != null) return false;
        if (adminPassword != null ? !adminPassword.equals(that.adminPassword) : that.adminPassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminName != null ? adminName.hashCode() : 0;
        result = 31 * result + (adminPassword != null ? adminPassword.hashCode() : 0);
        return result;
    }
}
