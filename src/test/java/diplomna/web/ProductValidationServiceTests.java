package diplomna.web;

import diplomna.model.entity.Category;
import diplomna.model.entity.Product;
import diplomna.service.ProductValidationService;
import diplomna.service.serviceImpl.ProductionValidationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductValidationServiceTests {
    private ProductValidationService service;

   @Before
    public void setupTest() {
       service = new ProductionValidationServiceImpl();
    }

    @Test
   public void isValidWithProduct_whenValid_true() {
        Product product = new Product();
       product.setCategory(new Category());
        boolean result = service.isValid(product);
        assertTrue(result);
    }

    @Test
    public void isValidWithProduct() {
        Product product = null;
        boolean result = service.isValid(product);
        assertTrue(result);
    }

    @Test
    public void isValidWithProductServiceModele() {
       Product product = null;
        boolean result = service.isValid(product);
       assertTrue(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenValid_true() {
        Product product = new Product();
        product.setCategory((new Category()));
        boolean result = service.isValid(product);
        assertTrue(result);
    }

   @Test
    public void t1() {
       Product product = new Product();
       product.setCategory(null);

        boolean result = service.isValid(product);
       assertFalse(result);
    }




  //  @Test
   // public void t2() {
   //     Product = new Product();
   //    product.setCategory(new ArrayList<>());

     //   boolean result = service.isValid(product);
    //   assertFalse(result);
 //  }
}
