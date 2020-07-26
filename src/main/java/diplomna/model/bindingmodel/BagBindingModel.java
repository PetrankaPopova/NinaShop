package diplomna.model.bindingmodel;

import diplomna.model.entity.Product;

import javax.persistence.OneToMany;
import java.util.List;

public class BagBindingModel {
    private List<Product> products;

    public BagBindingModel() {
    }

    @OneToMany
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
