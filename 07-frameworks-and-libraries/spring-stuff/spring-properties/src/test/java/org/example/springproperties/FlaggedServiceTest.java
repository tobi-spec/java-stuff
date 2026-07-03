package org.example.springproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = FlaggedService.class)
@TestPropertySource( properties = "isActivated=true")
public class FlaggedServiceTest {

    @Autowired
    FlaggedService flaggedService;

    @Test
    public void testPropertyBindings(){
        Assertions.assertTrue(flaggedService.getFlag());
    }
}
