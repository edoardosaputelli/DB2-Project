package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "marketing_question", schema = "db2_project_schema")
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
            mappedBy = "mQuestion",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<MarketingAnswerEntity> mList;

    @Basic
    @Column(name = "questionText", nullable = false, length = -1)
    private String questionText;

    public MarketingQuestionEntity () {}

    public MarketingQuestionEntity(QuestionnaireEntity questionnaire, String questionText) {
        this.questionnaire = questionnaire;
        this.questionText = questionText;
    }


    public QuestionnaireEntity getQuestionnaire() {
        return questionnaire;
    }

    public int getIdMarketingQuestion() {
        return idMarketingQuestion;
    }

    public void setIdMarketingQuestion(int idMarketingQuestion) {
        this.idMarketingQuestion = idMarketingQuestion;
    }



    /*public int getAssociatedQuestionnaire() {
        return associatedQuestionnaire;
    }*/



    /*public void setAssociatedQuestionnaire(int associatedQuestionnaire) {
        this.associatedQuestionnaire = associatedQuestionnaire;
    }*/


    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketingQuestionEntity that = (MarketingQuestionEntity) o;

        if (idMarketingQuestion != that.idMarketingQuestion) return false;
        if (associatedQuestionnaire != that.associatedQuestionnaire) return false;
        if (questionText != null ? !questionText.equals(that.questionText) : that.questionText != null) return false;

        return true;
    }*/

    /*@Override
    public int hashCode() {
        int result = idMarketingQuestion;
        result = 31 * result + associatedQuestionnaire;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        return result;
    }*/
}
