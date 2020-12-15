package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "questionnaire", schema = "db2_project_schema")
@NamedQuery(name = "QuestionnaireEntity.questOfGivenDay", query = "SELECT i FROM QuestionnaireEntity i JOIN ProductEntity p where i.productoftheday = p AND p.date = :givenDay")
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
            , cascade = CascadeType.ALL

    )
    private List<MarketingQuestionEntity> mList;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.REMOVE)
    private List<QuestionnaireResponseEntity> qRlist;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<StatisticalAnswerEntity> sList;

    public QuestionnaireEntity(){}

    public QuestionnaireEntity(ProductEntity product){
        this.productoftheday = product;
    }


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
