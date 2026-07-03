package org.example.springvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    public UserDto createUserDto(String username) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator  = validatorFactory.getValidator();

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Username violates constraints");
        }
        return userDto;
    }
}
