package diplomna.service;

import diplomna.model.entity.Product;

public interface ProductValidationService {
    boolean isValid(Product product);
}
