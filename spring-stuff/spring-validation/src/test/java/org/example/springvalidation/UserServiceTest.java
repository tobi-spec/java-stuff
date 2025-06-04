package org.example.springvalidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void testUserServiceOk() {
        UserDto userDto = userService.createUserDto("user");
        Assertions.assertEquals("user", userDto.getUsername());
    }

    @Test
    void testUserServiceTooLong() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUserDto("testuser"));
    }


}
