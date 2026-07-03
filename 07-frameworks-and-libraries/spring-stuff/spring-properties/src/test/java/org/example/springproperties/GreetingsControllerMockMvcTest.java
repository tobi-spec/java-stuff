package org.example.springproperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
@AutoConfigureMockMvc
public class GreetingsControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SinglePropertieService singlePropertieService;

    @MockitoBean
    private MultiplePropertiesService multiplePropertiesService;

    @Test
    public void testGreeting() throws Exception {
        when(singlePropertieService.getGreeting()).thenReturn("Hello from @value");
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello from @value")));
    }

    @Test
    public void testGreetings() throws Exception {
        when(multiplePropertiesService.getGerman()).thenReturn("Hallo von den properties");
        when(multiplePropertiesService.getEnglish()).thenReturn("Hello from properties");

        Map<String, String> expected = new HashMap<>();
        expected.put("german", "Hallo von den properties");
        expected.put("english", "Hello from properties");

        String expectedJson = new ObjectMapper().writeValueAsString(expected);

        mockMvc.perform(get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
