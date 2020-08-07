package diplomna.web;

import diplomna.model.entity.User;
import diplomna.model.service.UserServiceModel;
import diplomna.repository.ProductRepository;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.RoleService;
import diplomna.service.UserService;
import diplomna.service.serviceImpl.UserServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class UserServiceTest {

    private User testUser;
    private UserRepository mockedUserRepository;
    private RoleRepository mockedroleRepository;
    private RoleService mockedroleService;
    private ProductRepository mockedProductRepository;
    private ModelMapper mockedModelMapper;
    private BCryptPasswordEncoder mockedBCryptPasswordEncoder;
    private Tools mockedTools;

    public UserServiceTest() {

    }

    @Before()
    public void init() {
        this.testUser = new User() {{
            setId("4456d57a-fe5b-4b6a-a675-db29933236ac");
            setUsername("Pesho");
            setPassword("123");
        }};

        this.mockedUserRepository = Mockito.mock(UserRepository.class);
        this.mockedroleRepository = Mockito.mock(RoleRepository.class);
        this.mockedroleService = Mockito.mock(RoleService.class);
        this.mockedProductRepository = Mockito.mock(ProductRepository.class);
        this.mockedModelMapper = Mockito.mock(ModelMapper.class);
        this.mockedBCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        this.mockedUserRepository = Mockito.mock(UserRepository.class);
        this.mockedTools =  Mockito.mock(Tools.class);

    }

    @Test
    public void userService_GetUserWithCorrectUsername_shouldReturnCorrect() {

        Mockito.when(this.mockedUserRepository.findByUsername("Pesho"))
                .thenReturn(Optional.ofNullable(this.testUser));

        UserService userService = new UserServiceImp(this.mockedUserRepository);
        UserServiceModel model = userService.findById("c61c6d40-893d-4c27-a94c-389d9608f13a");
        User expected = this.testUser;

        Assert.assertEquals("4456d57a-fe5b-4b6a-a675-db29933236ac", expected.getId(), model.getId());
        Assert.assertEquals("Pesho", expected.getUsername(), model.getUsername());
        Assert.assertEquals("123", expected.getPassword(), model.getPassword());
    }
}


