package diplomna.service;

import diplomna.model.service.ProductServiceModel;
import diplomna.model.view.ProductViewModel;


import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductServiceModel findByProductName(String name);

    ProductServiceModel addProduct(ProductServiceModel productServiceModel) throws IOException;

    List<ProductViewModel> findAllProducts();

    void buyById(String id);

    void buyAll();

    void createProduct(ProductServiceModel product);


    //double getAllPrices();
}
