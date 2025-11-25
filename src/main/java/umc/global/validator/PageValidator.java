package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.global.annotation.ValidPage;

public class PageValidator implements ConstraintValidator<ValidPage,Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return value > 0;
    }
}
