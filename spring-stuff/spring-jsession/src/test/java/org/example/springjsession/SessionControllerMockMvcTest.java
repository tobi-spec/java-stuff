package org.example.springjsession;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@AutoConfigureMockMvc
public class SessionControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSessionWithMockMvc() throws Exception {
        MvcResult loginResult = mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpSession session = (MockHttpSession) loginResult.getRequest().getSession(false);

        mockMvc.perform(get("/secure-data").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("secure data")));

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("testUser logged out")));
    }
}
