package diplomna.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void when_getOneUser_returnFirst() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("one"))
                .andExpect(model().attributeExists("user"));
    }

}
