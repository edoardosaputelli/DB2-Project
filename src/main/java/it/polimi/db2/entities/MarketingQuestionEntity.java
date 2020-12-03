package it.polimi.db2.entities;

import javax.persistence.*;

@Entity
@Table(name = "marketing_question", schema = "db2_project_schema")
public class MarketingQuestionEntity {
    private int idMarketingQuestion;
    private int associatedQuestionnaire;
    private Object questionText;

    @Id
    @Column(name = "idMarketingQuestion", nullable = false)
    public int getIdMarketingQuestion() {
        return idMarketingQuestion;
    }

    public void setIdMarketingQuestion(int idMarketingQuestion) {
        this.idMarketingQuestion = idMarketingQuestion;
    }

    @Basic
    @Column(name = "associatedQuestionnaire", nullable = false)
    public int getAssociatedQuestionnaire() {
        return associatedQuestionnaire;
    }

    public void setAssociatedQuestionnaire(int associatedQuestionnaire) {
        this.associatedQuestionnaire = associatedQuestionnaire;
    }

    @Basic
    @Column(name = "questionText", nullable = false, length = -1)
    public Object getQuestionText() {
        return questionText;
    }

    public void setQuestionText(Object questionText) {
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
    }
}
