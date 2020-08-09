package diplomna.service.serviceImpl;

import diplomna.model.entity.Product;
import diplomna.service.ProductValidationService;

public class ProductionValidationServiceImpl implements ProductValidationService {
    @Override
    public boolean isValid(Product product) {

        return true;
    }
}
