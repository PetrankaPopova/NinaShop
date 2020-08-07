package diplomna.web;

import diplomna.model.entity.User;
import diplomna.model.service.UserServiceModel;
import diplomna.repository.RoleRepository;
import diplomna.repository.UserRepository;
import diplomna.service.UserService;
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
public class UserServiceTest {

    private Optional<User> testUser;
    private UserRepository mockedUserRepository;
    private ModelMapper mockedModelMapper;
    private RoleRepository roleRepository;

    public UserServiceTest() {

    }

    @Before()
    public void init() {
        this.testUser = Optional.of(new User() {{
            setId("aaaa");
            setUsername("Pesho");
            setPassword("123");
        }});

        this.mockedUserRepository = Mockito.mock(UserRepository.class);
        this.mockedModelMapper = Mockito.mock(ModelMapper.class);

    }

    @Test
    public void userService_GetUserWithCorrectUsername_shouldReturnCorrect() {


        Mockito.when(this.mockedUserRepository.findUserByUsername("Pesho"))
                .thenReturn(this.testUser);

        Optional<User> mockedUsers = this.mockedUserRepository.findUserByUsername("Pesho");
        Optional<User> mockedUsers2 = this.mockedUserRepository.findUserByUsername("Pesho");
        UserServiceModel usm = new UserServiceModel();
        usm.setUsername("Pesho");
        Mockito.when(this.mockedModelMapper.map(mockedUsers.get(), UserServiceModel.class))
                .thenReturn(usm);

        UserService userService = new UserServiceImp(this.mockedUserRepository, roleRepository, null, null, this.mockedModelMapper, null, null);
        //Optional<User> user = this.mockedUserRepository.findByUsername("Pesho");
       // UserServiceModel user2 = userService.findUserByUsername("Pesho");
        Optional<User> expected = this.testUser;
        UserServiceModel actual = userService.findByUsername("Pesho");

        User findedUser = this.mockedUserRepository.findUserByUsername("Pesho").orElse(null);
        Optional<User> mockedUsers3 = this.mockedUserRepository.findUserByUsername("Pesho");


       // Assert.assertEquals("4456d57a-fe5b-4b6a-a675-db29933236ac", expected.getId(), model.getId());
        Assert.assertEquals("Pesho", expected.get().getUsername(), actual.getUsername());
       // Assert.assertEquals("123", expected.getPassword(), model.getPassword());
       // System.out.println(user2);
    }
}


