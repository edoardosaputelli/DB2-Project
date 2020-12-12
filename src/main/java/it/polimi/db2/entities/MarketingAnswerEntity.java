package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "marketing_answer", schema = "db2_project_schema")
public class MarketingAnswerEntity implements Serializable {
    @Id
    @Column(name = "idMarketing_answer", nullable = false)
    private int idMarketingAnswer;

    @ManyToOne
    @JoinColumn(
            name = "associatedMarketingQuestion"
    )
    private MarketingQuestionEntity mQuestion;

    @ManyToOne
    @JoinColumn (name = "associatedUser")
    private UserEntity user;

    @Basic
    @Column(name = "answerText", nullable = false)
    private String answerText;

    public MarketingAnswerEntity() {}

    public MarketingAnswerEntity(MarketingQuestionEntity question, UserEntity user, String answerText) {
        this.mQuestion = question;
        this.user = user;
        this.answerText = answerText;
    }



    public int getIdMarketingAnswer() {
        return idMarketingAnswer;
    }

    public void setIdMarketingAnswer(int idMarketingAnswer) {
        this.idMarketingAnswer = idMarketingAnswer;
    }


    public MarketingQuestionEntity getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(MarketingQuestionEntity mQuestion) {
        this.mQuestion = mQuestion;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
    /*




    @Basic
    @Column(name = "associatedMarketingQuestion", nullable = false)
    public int getAssociatedMarketingQuestion() {
        return associatedMarketingQuestion;
    }

    public void setAssociatedMarketingQuestion(int associatedMarketingQuestion) {
        this.associatedMarketingQuestion = associatedMarketingQuestion;
    }

    @Basic
    @Column(name = "associatedUser", nullable = false)
    public int getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(int associatedUser) {
        this.associatedUser = associatedUser;
    }


    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketingAnswerEntity that = (MarketingAnswerEntity) o;

        if (idMarketingAnswer != that.idMarketingAnswer) return false;
        if (associatedMarketingQuestion != that.associatedMarketingQuestion) return false;
        if (associatedUser != that.associatedUser) return false;
        if (answerText != null ? !answerText.equals(that.answerText) : that.answerText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMarketingAnswer;
        result = 31 * result + associatedMarketingQuestion;
        result = 31 * result + associatedUser;
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        return result;
    }*/
}
