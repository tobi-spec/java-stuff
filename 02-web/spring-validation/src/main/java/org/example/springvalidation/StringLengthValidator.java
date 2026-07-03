package org.example.springvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringLengthValidator implements ConstraintValidator<ValidStringLength, String> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidStringLength validStringLength) {
        this.min = validStringLength.min();
        this.max = validStringLength.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
           return true;
        }

        return value.length() >= min && value.length() <= max;
    }
}
