package org.example.springproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SinglePropertiesServiceTest {

    private final SinglePropertieService singlePropertieService = new SinglePropertieService("Hello from Test");

    @Test
    public void testGetGreeting() {
        String actual = singlePropertieService.getGreeting();
        Assertions.assertEquals(actual, "Hello from Test");
    }

}
