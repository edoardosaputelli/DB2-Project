package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "marketing_question", schema = "db2_project_schema")
@NamedQuery(name = "MarketingQuestionEntity.findQuestionByQuest", query = "SELECT r FROM MarketingQuestionEntity r  WHERE r.questionnaire.idQuestionnaire = ?1")
public class MarketingQuestionEntity implements Serializable {
    @Id
    @Column(name = "idMarketingQuestion", nullable = false)
    private int idMarketingQuestion;

    @ManyToOne
    @JoinColumn(
            name = "associatedQuestionnaire"
    )
    private QuestionnaireEntity questionnaire;

    @OneToMany(
            mappedBy = "mQuestion"
    )
    private List<MarketingAnswerEntity> mList;



    public int getIdMarketingQuestion() {
        return idMarketingQuestion;
    }

    public void setIdMarketingQuestion(int idMarketingQuestion) {
        this.idMarketingQuestion = idMarketingQuestion;
    }


    /*
    @Basic
    @Column(name = "associatedQuestionnaire", nullable = false)
    private int associatedQuestionnaire;
    @Basic
    @Column(name = "questionText", nullable = false, length = -1)
    private String questionText;





    public int getAssociatedQuestionnaire() {
        return associatedQuestionnaire;
    }

    public void setAssociatedQuestionnaire(int associatedQuestionnaire) {
        this.associatedQuestionnaire = associatedQuestionnaire;
    }


    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketingQuestionEntity that = (MarketingQuestionEntity) o;

        if (idMarketingQuestion != that.idMarketingQuestion) return false;
        if (associatedQuestionnaire != that.associatedQuestionnaire) return false;
        if (questionText != null ? !questionText.equals(that.questionText) : that.questionText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMarketingQuestion;
        result = 31 * result + associatedQuestionnaire;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        return result;
    }*/
}
