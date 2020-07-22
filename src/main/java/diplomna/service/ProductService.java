package diplomna.service;

import diplomna.model.service.ProductServiceModel;
import diplomna.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel findByProductName(String name);

    ProductServiceModel addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllProducts();

    void buyById(String id);

    void buyAll();


    //double getAllPrices();
}
