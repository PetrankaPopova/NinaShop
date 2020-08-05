package diplomna.web;

import diplomna.model.entity.Product;
import diplomna.model.service.CategoryServiceModel;
import diplomna.model.service.ProductServiceModel;
import diplomna.repository.ProductRepository;
import diplomna.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository mockProductRepository;

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void createProduct_whenValid_productCreated() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategory(new CategoryServiceModel().getName);
        when(mockProductRepository.save(any()))
                .thenReturn(new Product());

        service.createProduct(product);
        verify(mockProductRepository)
                .save(any());
    }

    @Test(expected = NullPointerException.class)
    public void createProduct_whenNull_throw() {
        service.createProduct(null);
        verify(mockProductRepository)
                .save(any());
    }
}
