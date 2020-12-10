package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "questionnaire", schema = "db2_project_schema")
public class QuestionnaireEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuestionnaire", nullable = false)
    private int idQuestionnaire;


    @OneToOne
    @JoinColumn(
            name = "associatedProduct"
    )
    private ProductEntity productoftheday;
    @OneToMany(
            mappedBy = "questionnaire"
    )
    private List<MarketingQuestionEntity> mList;

    @OneToMany(mappedBy = "questionnaire")
    private List<QuestionnaireResponseEntity> qRlist;


    public int getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(int idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    public ProductEntity getProductoftheday() {
        return productoftheday;
    }

    public void setProductoftheday(ProductEntity productoftheday) {
        this.productoftheday = productoftheday;
    }

    public List<MarketingQuestionEntity> getmList() {
        return mList;
    }

    public void setmList(List<MarketingQuestionEntity> mList) {
        this.mList = mList;
    }

    public List<QuestionnaireResponseEntity> getqRlist() {
        return qRlist;
    }

    public void setqRlist(List<QuestionnaireResponseEntity> qRlist) {
        this.qRlist = qRlist;
    }


    /*
    private int associatedProduct;





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
    }*/
}
