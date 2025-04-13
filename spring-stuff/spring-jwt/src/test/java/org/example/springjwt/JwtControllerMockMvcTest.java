package org.example.springjwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JwtController.class)
@AutoConfigureMockMvc
public class JwtControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    JwtService jwtService;

    @Test
    public void testLogin() throws Exception {
        when(jwtService.createJWTToken("testUser")).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdCb290QmFja2VuZCIsImF1ZCI6IlNwcmluZ1Rlc3QiLCJzdWIiOiJ0ZXN0VXNlciIsImV4cCI6MTc0NDU0NDE3N30.x7875deM2ypAxsmLRjtxSr8So-8nXkx06FQMbOg9vHY");
        mockMvc.perform(post("/login?username=testUser"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTcHJpbmdCb290QmFja2VuZCIsImF1ZCI6IlNwcmluZ1Rlc3QiLCJzdWIiOiJ0ZXN0VXNlciIsImV4cCI6MTc0NDU0NDE3N30.x7875deM2ypAxsmLRjtxSr8So-8nXkx06FQMbOg9vHY")));
    }

    @Test
    public void testSecureData() throws Exception {

    }
}
