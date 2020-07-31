/*
package diplomna.model.entity;

import diplomna.model.entity.BaseEntity;
import diplomna.model.entity.Product;
import diplomna.model.service.BaseEntityService;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order")
public class Offer extends BaseEntity {
    private Product product;
    private BigDecimal price;

    public Offer() {
    }

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
*/
