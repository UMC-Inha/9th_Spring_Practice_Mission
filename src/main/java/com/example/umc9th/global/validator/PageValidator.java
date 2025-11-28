package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.PageCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageValidator implements ConstraintValidator<PageCheck, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null || value < 1) {
            return false;
        }
        return true;
    }
}
