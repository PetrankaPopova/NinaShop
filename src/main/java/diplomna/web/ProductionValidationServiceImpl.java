package diplomna.web;

import diplomna.model.entity.Product;

public class ProductionValidationServiceImpl implements ProductValidationService {
    @Override
    public boolean isValid(Product product) {
        return true;
    }
}
