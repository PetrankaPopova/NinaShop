package diplomna.web;

import diplomna.model.entity.User;
import diplomna.repository.UserRepository;
import diplomna.service.UserService;
import diplomna.service.serviceImpl.UserServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class UserServiceTest {
    private User testUser;
    private UserRepository mockedUserRepository;
    private User actual;

    @Before()
    public void init() {
        this.testUser = new User() {{
            setId("");
            setUsername("Pesho");
            setPassword("123");
        }};

        this.mockedUserRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    public void userService_GetUserWithCorrectUsername_shouldReturnCorrect() {

        Mockito.when(this.mockedUserRepository.findByUsername("Pesho"))
                .thenReturn(java.util.Optional.ofNullable(this.testUser));

        UserService userService = new UserServiceImp(this.mockedUserRepository);
        User expected = this.testUser;

        Assert.assertEquals("Broken", expected.getId(), actual.getId());
        Assert.assertEquals("Broken", expected.getUsername(), actual.getUsername());
        Assert.assertEquals("Broken", expected.getPassword(), actual.getPassword());
    }
}


