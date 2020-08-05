package diplomna.web;

import diplomna.model.entity.Product;

public interface ProductValidationService {
    boolean isValid(Product product);
}
