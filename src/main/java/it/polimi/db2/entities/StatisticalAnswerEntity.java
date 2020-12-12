package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statistical_answer", schema = "db2_project_schema")
public class StatisticalAnswerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstatistical_answer", nullable = false)
    private int idstatisticalAnswer;


    @ManyToOne
    @JoinColumn(
            name = "associatedStatisticalQuestion"
    )
    private StatisticalQuestionEntity question;

    @ManyToOne
    @JoinColumn(
            name = "targetUser"
    )
    private UserEntity user;

    @ManyToOne
    @JoinColumn(
            name = "targetQuestionnaire"
    )
    private QuestionnaireEntity questionnaire;

    @Column(name = "answerText", nullable = false)
    private String answerText;

    public StatisticalAnswerEntity() {}

    public StatisticalAnswerEntity(UserEntity user, StatisticalQuestionEntity question, String answerText, QuestionnaireEntity questionnaire) {
        this.question = question;
        this.user = user;
        this.answerText = answerText;
        this.questionnaire = questionnaire;
    }


    public StatisticalQuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(StatisticalQuestionEntity question) {
        this.question = question;
    }


    public int getIdstatisticalAnswer() {
        return idstatisticalAnswer;
    }

    public void setIdstatisticalAnswer(int idstatisticalAnswer) {
        this.idstatisticalAnswer = idstatisticalAnswer;
    }

/*
    public int getAssociatedStatisticalQuestion() {
        return associatedStatisticalQuestion;
    }

    public void setAssociatedStatisticalQuestion(int associatedStatisticalQuestion) {
        this.associatedStatisticalQuestion = associatedStatisticalQuestion;
    }


    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }


    public int getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(int targetUser) {
        this.targetUser = targetUser;
    }


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
    }*/
}
