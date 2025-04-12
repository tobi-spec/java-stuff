package org.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    HelloService helloService = new HelloService();

    @Test
    public void testGreeting() {
        Assertions.assertEquals(helloService.greet(), "Hello from Spring");
    }
}
