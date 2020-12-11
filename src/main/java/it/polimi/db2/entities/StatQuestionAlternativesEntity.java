package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statquestionalternatives", schema = "db2_project_schema")
public class StatQuestionAlternativesEntity implements Serializable {

    @Id
    @Column(name = "idstatQuestionAlternatives", nullable = false)
    private int idstatQuestionAlternatives;

    @Basic
    @Column(name = "alternativeText", nullable = false, length = 45)
    private String alternativeText;


    //da rivedere con gli altri
    @ManyToOne
    @JoinColumn(name ="associatedStatQuestion")
    private StatisticalQuestionEntity statisticalQuestion;


    public StatisticalQuestionEntity getStatisticalQuestion() {
        return statisticalQuestion;
    }

    public void setStatisticalQuestion(StatisticalQuestionEntity statisticalQuestion) {
        this.statisticalQuestion = statisticalQuestion;
    }

    public int getIdstatQuestionAlternatives() {
        return idstatQuestionAlternatives;
    }

    public void setIdstatQuestionAlternatives(int idstatQuestionAlternatives) {
        this.idstatQuestionAlternatives = idstatQuestionAlternatives;
    }


    public String getAlternativeText() {
        return alternativeText;
    }

    public void setAlternativeText(String alternativeText) {
        this.alternativeText = alternativeText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatQuestionAlternativesEntity that = (StatQuestionAlternativesEntity) o;

        if (idstatQuestionAlternatives != that.idstatQuestionAlternatives) return false;
        if (alternativeText != null ? !alternativeText.equals(that.alternativeText) : that.alternativeText != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstatQuestionAlternatives;
        result = 31 * result + (alternativeText != null ? alternativeText.hashCode() : 0);
        return result;
    }
}
