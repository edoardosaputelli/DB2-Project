package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "statistical_question", schema = "db2_project_schema")
public class StatisticalQuestionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatisticalQuestion", nullable = false)
    private int idStatisticalQuestion;

    @Basic
    @Column(name = "questionText", nullable = false, length = -1)
    private String questionText;


    public int getIdStatisticalQuestion() {
        return idStatisticalQuestion;
    }

    public void setIdStatisticalQuestion(int idStatisticalQuestion) {
        this.idStatisticalQuestion = idStatisticalQuestion;
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

        StatisticalQuestionEntity that = (StatisticalQuestionEntity) o;

        if (idStatisticalQuestion != that.idStatisticalQuestion) return false;
        if (questionText != null ? !questionText.equals(that.questionText) : that.questionText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatisticalQuestion;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        return result;
    }
}
