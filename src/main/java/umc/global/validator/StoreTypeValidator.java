package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import umc.domain.store.entity.StoreType;
import umc.global.annotation.ValidStoreType;

public class StoreTypeValidator implements ConstraintValidator<ValidStoreType, String> {

    private Set<String> allowedValues;

    @Override
    public void initialize(ValidStoreType constraintAnnotation) {
        allowedValues = Arrays.stream(StoreType.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // null은 @NotNull이 처리

        boolean valid = allowedValues.contains(value);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "가게 타입은 " + String.join(", ", allowedValues) + " 중 하나여야 합니다."
            ).addConstraintViolation();
        }

        return valid;
    }
}
