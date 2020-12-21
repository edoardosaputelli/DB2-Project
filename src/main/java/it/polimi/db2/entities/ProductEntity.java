package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "product", schema = "db2_project_schema")
@NamedQueries({
        @NamedQuery(name = "ProductEntity.getProductOfGivenDay", query = "SELECT r FROM ProductEntity r WHERE r.date = :givenDate")
})
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct", nullable = false)
    private int idProduct;


    @OneToOne(
            mappedBy = "productoftheday"
    )
    private QuestionnaireEntity questionnaire;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY )
    private List<ReviewEntity> rList;


    @Basic
    @Column(name = "productName", nullable = false, length = 45)
    private String productName;

    @Basic
    @Column(name = "productImage", nullable = false)
    private byte[] productImage;

    @Basic
    @Column(name = "date", nullable = false)
    private Date date;



    public ProductEntity(){}

    public ProductEntity(String productName, java.sql.Date date, byte[] productImage){

        this.productName = productName;
        this.date = date;
        this.productImage = productImage;

    }




    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public QuestionnaireEntity getQuestionnaire() {
        return questionnaire;
    }

    public List<ReviewEntity> getrList() {
        return rList;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (idProduct != that.idProduct) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (!Arrays.equals(productImage, that.productImage)) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduct;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(productImage);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

}
