package it.polimi.db2.entities;

import javax.persistence.*;

@Entity
@Table(name = "questionnaire", schema = "db2_project_schema")
public class QuestionnaireEntity {
    private int idQuestionnaire;
    private int associatedProduct;

    @Id
    @Column(name = "idQuestionnaire", nullable = false)
    public int getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(int idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    @Basic
    @Column(name = "associatedProduct", nullable = false)
    public int getAssociatedProduct() {
        return associatedProduct;
    }

    public void setAssociatedProduct(int associatedProduct) {
        this.associatedProduct = associatedProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionnaireEntity that = (QuestionnaireEntity) o;

        if (idQuestionnaire != that.idQuestionnaire) return false;
        if (associatedProduct != that.associatedProduct) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idQuestionnaire;
        result = 31 * result + associatedProduct;
        return result;
    }
}
