package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "questionnaire_response", schema = "db2_project_schema")
public class QuestionnaireResponseEntity implements Serializable {
    @Basic
    @Column(name = "flagCancelled", nullable = false)
    private byte flagCancelled;

    @Id
    @Column(name = "idBridge", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBridge;

    @ManyToOne
    @JoinColumn(name ="relatedQuestionnaire")
    private QuestionnaireEntity questionnaire;

    @ManyToOne
    @JoinColumn(name ="relatedUser")
    private UserEntity user;

    public QuestionnaireResponseEntity(){}

    public QuestionnaireResponseEntity (QuestionnaireEntity questionnaire, UserEntity user, byte flagCancelled) {
        this.questionnaire = questionnaire;
        this.user = user;
        this.flagCancelled = flagCancelled;
    }


    public byte getFlagCancelled() {
        return flagCancelled;
    }

    public void setFlagCancelled(byte flagCancelled) {
        this.flagCancelled = flagCancelled;
    }

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

        QuestionnaireResponseEntity that = (QuestionnaireResponseEntity) o;

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
