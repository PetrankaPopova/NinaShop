package diplomna.model.entity;

import diplomna.model.Purchase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "bags")
public class Bag extends BaseEntity {

    private List<Product> products;

    public Bag() {
    }

    @OneToMany
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
