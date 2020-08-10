package diplomna.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import diplomna.model.entity.User;
import diplomna.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository mockUserRepository;

    private User user1, user2;

    private String NEW_USER_ID = "5";
    private int NON_EXISTANT_USER_ID = 42;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setId("1");
        user1.setUsername("Author 1");
        user2 = new User();
        user2.setId("2");
        user2.setUsername("Author 2");

        User userToSave = null;

        when(mockUserRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(mockUserRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
        when(mockUserRepository.findAll()).thenReturn(List.of(user1, user2));

        when(mockUserRepository.save(any())).thenAnswer(
                (Answer<User>) invocation -> {
                    User authorToSave = invocation.getArgument(0);
                    userToSave.setId(NEW_USER_ID);
                    return userToSave;
                }
        );
    }



    @Test
    public void testUsersReturnsCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/users")).
                andExpect(status().isOk());
    }

    @Test
    public void testUserNotFound() throws Exception {
        this.mockMvc.perform(get("/users/{id}", NON_EXISTANT_USER_ID)).
                andExpect(status().isNotFound());
    }

    @Test
    public void testUser1Found() throws Exception {
        this.mockMvc.
                perform(get("/users/{id}", user1.getId())).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name", is(user1.getUsername())));
    }

    @Test
    public void testUsers2Found() throws Exception {
        this.mockMvc.
                perform(get("/users/{id}", user2.getId())).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name", is(user2.getUsername())));
    }

    @Test
    public void testAllUsers() throws Exception {
        this.mockMvc.
                perform(get("/users")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].id", is(user1.getId()))).
                andExpect(jsonPath("$.[0].name", is(user1.getUsername()))).
                andExpect(jsonPath("$.[1].id", is(user2.getId()))).
                andExpect(jsonPath("$.[1].name", is(user2.getUsername())));
    }

    @Test
    public void testCreateNewAuthor() throws Exception {
        //User newUserExpected = new User().setUsername("NEW AUTHOR");
        //String json = objectMapper.writeValueAsString(newUserExpected);

        this.mockMvc.perform(
                post("/users").
                        contentType(MediaType.APPLICATION_JSON).
                        // content(json).
                                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated());

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(mockUserRepository, times(1)).save(argument.capture());

        User newAuthorActual = argument.getValue();

        // Assertions.assertEquals(newUserExpected.getUsername(), newAuthorActual.getUsername());
        Assertions.assertEquals(NEW_USER_ID, newAuthorActual.getId());
    }
}
