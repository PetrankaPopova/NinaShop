package diplomna.web;

import diplomna.model.entity.Product;
import diplomna.model.entity.User;
import diplomna.model.service.CategoryServiceModel;
import diplomna.model.service.ProductServiceModel;
import diplomna.model.service.UserServiceModel;
import diplomna.repository.ProductRepository;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.ProductService;
import diplomna.service.UserService;
import diplomna.service.serviceImpl.ProductServiceImpl;
import diplomna.service.serviceImpl.UserServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    private Optional<Product> testProduct;
    private ProductRepository mockedProductRepository;
    private ModelMapper mockedModelMapper;


    public ProductServiceTests() {

    }

    @Before()
    public void init() {
        this.testProduct = Optional.of(new Product() {{
            setId("aaaa");
           // setCategory();
            setUrl("http://res.cloudinary.com/dgfiguhlk/image/upload/v1596903289/if236gbj9drgnnkenuji.jpg");
            setDescription("Long black dress");
            setName("Dress");
            setPrice(BigDecimal.valueOf(480));
        }});

        this.mockedProductRepository = Mockito.mock(ProductRepository.class);
        this.mockedModelMapper = Mockito.mock(ModelMapper.class);

    }

    @Test
    public void userService_GetUserWithCorrectUsername_shouldReturnCorrect() {


        Mockito.when(this.mockedProductRepository.findByName("Pesho"))
                .thenReturn(this.testProduct);

        Optional<Product> mockedProducts = this.mockedProductRepository.findByName("Pesho");
        Optional<Product> mockedProduct2 = this.mockedProductRepository.findByName("Pesho");
        ProductServiceModel usm = new ProductServiceModel();
        usm.setName("Pesho");
        Mockito.when(this.mockedModelMapper.map(mockedProducts.get(), ProductServiceModel.class))
                .thenReturn(usm);

        ProductService productService = new ProductServiceImpl(this.mockedProductRepository, null, null);
        //Optional<User> user = this.mockedUserRepository.findByUsername("Pesho");
        // UserServiceModel user2 = userService.findUserByUsername("Pesho");
        Optional<Product> expected = this.testProduct;
        ProductServiceModel actual = productService.findByProductName("Pesho");

        Product findedProduct = this.mockedProductRepository.findByName("Pesho").orElse(null);
        Optional<Product> mockedUsers3 = this.mockedProductRepository.findByName("Pesho");


        // Assert.assertEquals("4456d57a-fe5b-4b6a-a675-db29933236ac", expected.getId(), model.getId());
        Assert.assertEquals("Pesho", expected.get().getName(), actual.getName());
        // Assert.assertEquals("123", expected.getPassword(), model.getPassword());
        // System.out.println(user2);
    }
}

