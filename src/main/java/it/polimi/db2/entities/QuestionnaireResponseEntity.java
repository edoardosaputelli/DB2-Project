package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "questionnaire_response", schema = "db2_project_schema")
@NamedQuery(name = "QuestionnaireResponseEntity.alreadyDidIt", query = "SELECT i FROM QuestionnaireResponseEntity i WHERE i.user.idUser = ?1" +
        " AND i.questionnaire = ?2")
public class QuestionnaireResponseEntity implements Serializable {
    @Basic
    @Column(name = "flagCancelled", nullable = false)
    private boolean flagCancelled;

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

    public QuestionnaireResponseEntity (QuestionnaireEntity questionnaire, UserEntity user, boolean flagCancelled) {
        this.questionnaire = questionnaire;
        this.user = user;
        this.flagCancelled = flagCancelled;
    }


    public boolean getFlagCancelled() {
        return flagCancelled;
    }

    public void setFlagCancelled(boolean flagCancelled) {
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


}
