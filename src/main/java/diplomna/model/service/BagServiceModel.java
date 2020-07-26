package diplomna.model.service;

import diplomna.model.entity.Product;

import javax.persistence.OneToMany;
import java.util.List;

public class BagServiceModel extends BaseEntityService {

    private List<Product> products;

    public BagServiceModel() {
    }

    @OneToMany
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
