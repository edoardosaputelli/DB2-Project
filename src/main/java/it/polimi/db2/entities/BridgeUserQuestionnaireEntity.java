package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bridge user-questionnaire", schema = "db2_project_schema")
public class BridgeUserQuestionnaireEntity implements Serializable {
    private byte flagCancelled;
    private int idBridge;

    @Basic
    @Column(name = "flagCancelled", nullable = false)
    public byte getFlagCancelled() {
        return flagCancelled;
    }

    public void setFlagCancelled(byte flagCancelled) {
        this.flagCancelled = flagCancelled;
    }

    @Id
    @Column(name = "idBridge", nullable = false)
    public int getIdBridge() {
        return idBridge;
    }

    public void setIdBridge(int idBridge) {
        this.idBridge = idBridge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BridgeUserQuestionnaireEntity that = (BridgeUserQuestionnaireEntity) o;

        if (flagCancelled != that.flagCancelled) return false;
        if (idBridge != that.idBridge) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) flagCancelled;
        result = 31 * result + idBridge;
        return result;
    }
}
