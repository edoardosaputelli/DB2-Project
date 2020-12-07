package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statistical_answer", schema = "db2_project_schema")
public class StatisticalAnswerEntity implements Serializable {
    private int idstatisticalAnswer;
    private int associatedStatisticalQuestion;
    private String answerText;
    private int targetUser;
    private int targetQuestionnaire;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstatistical_answer", nullable = false)
    public int getIdstatisticalAnswer() {
        return idstatisticalAnswer;
    }

    public void setIdstatisticalAnswer(int idstatisticalAnswer) {
        this.idstatisticalAnswer = idstatisticalAnswer;
    }

    @Basic
    @Column(name = "associatedStatisticalQuestion", nullable = false)
    public int getAssociatedStatisticalQuestion() {
        return associatedStatisticalQuestion;
    }

    public void setAssociatedStatisticalQuestion(int associatedStatisticalQuestion) {
        this.associatedStatisticalQuestion = associatedStatisticalQuestion;
    }

    @Basic
    @Column(name = "answerText", nullable = true, length = -1)
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Basic
    @Column(name = "targetUser", nullable = false)
    public int getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(int targetUser) {
        this.targetUser = targetUser;
    }

    @Basic
    @Column(name = "targetQuestionnaire", nullable = false)
    public int getTargetQuestionnaire() {
        return targetQuestionnaire;
    }

    public void setTargetQuestionnaire(int targetQuestionnaire) {
        this.targetQuestionnaire = targetQuestionnaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticalAnswerEntity that = (StatisticalAnswerEntity) o;

        if (idstatisticalAnswer != that.idstatisticalAnswer) return false;
        if (associatedStatisticalQuestion != that.associatedStatisticalQuestion) return false;
        if (targetUser != that.targetUser) return false;
        if (targetQuestionnaire != that.targetQuestionnaire) return false;
        if (answerText != null ? !answerText.equals(that.answerText) : that.answerText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstatisticalAnswer;
        result = 31 * result + associatedStatisticalQuestion;
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + targetUser;
        result = 31 * result + targetQuestionnaire;
        return result;
    }
}
