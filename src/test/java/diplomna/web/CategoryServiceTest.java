package diplomna.web;

import diplomna.model.entity.Category;
import diplomna.model.entity.CategoryName;
import diplomna.model.entity.User;
import diplomna.model.service.CategoryServiceModel;
import diplomna.model.service.UserServiceModel;
import diplomna.repository.CategoryRepository;
import diplomna.repository.UserRepository;
import diplomna.service.CategoryService;
import diplomna.service.UserService;
import diplomna.service.serviceImpl.CategoryServiceImpl;
import diplomna.service.serviceImpl.UserServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    private Optional<Category> testUser;
    private CategoryRepository mockedCategoryRepository;
    private ModelMapper mockedModelMapper;
    private CategoryName categoryName;
    private Category categoryRepository;


    public CategoryServiceTest() {

    }

    @Before()
    public void init() {
        this.testUser = Optional.of(new Category() {{
            setId("aaaa");
            setCategoryName(categoryName);
            setDescription("Dress");
        }});

        this.mockedCategoryRepository = Mockito.mock(CategoryRepository.class);
        this.mockedModelMapper = Mockito.mock(ModelMapper.class);

    }

    @Test
    public void userService_GetUserWithCorrectUsername_shouldReturnCorrect() {


        Mockito.when(this.mockedCategoryRepository.findByCategoryName(categoryName))
                .thenReturn(this.testUser);

        Optional<Category> mockedUsers = this.mockedCategoryRepository.findByCategoryName(categoryName);
        Optional<Category> mockedUsers2 = this.mockedCategoryRepository.findByCategoryName(categoryName);
        UserServiceModel usm = new UserServiceModel();
        usm.setUsername("Pesho");
        Mockito.when(this.mockedModelMapper.map(mockedUsers.get(), UserServiceModel.class))
                .thenReturn(usm);

        CategoryService categoryService = new CategoryServiceImpl(this.mockedCategoryRepository, mockedModelMapper);
        //Optional<User> user = this.mockedUserRepository.findByUsername("Pesho");
        // UserServiceModel user2 = userService.findUserByUsername("Pesho");
        Optional<Category> expected = this.testUser;
        System.out.println(expected);
        CategoryServiceModel actual = this.mockedModelMapper.map(categoryService.getByName(categoryName), CategoryServiceModel.class);
        System.out.println(actual);
        Category findedCategory = this.mockedCategoryRepository.findByCategoryName(categoryName).orElse(null);
       // System.out.println(findedCategory);
        Optional<Category> mockedUsers3 = this.mockedCategoryRepository.findByCategoryName(categoryName);
      //  System.out.println(mockedUsers3);


        // Assert.assertEquals("4456d57a-fe5b-4b6a-a675-db29933236ac", expected.getId(), model.getId());
        Assert.assertEquals(String.valueOf(categoryName), expected.get().getCategoryName(), actual.getCategoryName());
        // Assert.assertEquals("123", expected.getPassword(), model.getPassword());
        // System.out.println(user2);
    }
}