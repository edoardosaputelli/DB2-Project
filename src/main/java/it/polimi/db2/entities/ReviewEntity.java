package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review", schema = "db2_project_schema")
public class ReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreview", nullable = false)
    private int idreview;

    @Basic
    @Column(name = "reviewText", nullable = false)
    private String reviewText;
    @Basic
    @Column(name = "stars", nullable = false)
    private byte stars;

    @ManyToOne
    @JoinColumn(
            name = "relatedProduct"
    )
    private ProductEntity product;

    public int getIdreview() {
        return idreview;
    }

    public void setIdreview(int idreview) {
        this.idreview = idreview;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public byte getStars() {
        return stars;
    }

    public void setStars(byte stars) {
        this.stars = stars;
    }

    /*

    @Basic
    @Column(name = "relatedProduct", nullable = false)
    public int getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(int relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewEntity that = (ReviewEntity) o;

        if (idreview != that.idreview) return false;
        if (stars != that.stars) return false;
        if (relatedProduct != that.relatedProduct) return false;
        if (reviewText != null ? !reviewText.equals(that.reviewText) : that.reviewText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idreview;
        result = 31 * result + (reviewText != null ? reviewText.hashCode() : 0);
        result = 31 * result + (int) stars;
        result = 31 * result + relatedProduct;
        return result;
    }*/
}
