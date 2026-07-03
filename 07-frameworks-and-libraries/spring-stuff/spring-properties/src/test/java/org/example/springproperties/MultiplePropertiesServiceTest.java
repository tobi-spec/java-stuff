package org.example.springproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource( properties = {
        "greetings.german=Hallo Test",
        "greetings.english=Hello Test"
})
public class MultiplePropertiesServiceTest {

    @Autowired
    MultiplePropertiesService multiplePropertiesService;

    @Test
    public void testPropertyBindings() {
        Assertions.assertEquals(multiplePropertiesService.getGerman(), "Hallo Test");
        Assertions.assertEquals(multiplePropertiesService.getEnglish(), "Hello Test");
    }
}
