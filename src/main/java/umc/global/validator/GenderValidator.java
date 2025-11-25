package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import umc.domain.member.entity.MemberGender;
import umc.global.annotation.ValidGender;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    private Set<String> allowedValues;

    @Override
    public void initialize(ValidGender constraintAnnotation) {
        allowedValues = Arrays.stream(MemberGender.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        boolean valid = allowedValues.contains(value);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "성별은 " + String.join(", ", allowedValues) + " 중 하나여야 합니다."
            ).addConstraintViolation();
        }

        return valid;
    }
}