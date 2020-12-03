package it.polimi.db2.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@Table(name = "product", schema = "db2_project_schema")
public class ProductEntity {
    private int idProduct;
    private String productName;
    private byte[] productImage;
    private Timestamp date;

    @Id
    @Column(name = "idProduct", nullable = false)
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "productName", nullable = false, length = 45)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "productImage", nullable = false)
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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
