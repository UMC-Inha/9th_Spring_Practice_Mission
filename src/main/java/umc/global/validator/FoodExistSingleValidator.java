package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.domain.food.code.FoodErrorCode;
import umc.domain.food.repository.FoodRepository;
import umc.global.annotation.ExistFood;

@Component
@RequiredArgsConstructor
public class FoodExistSingleValidator implements ConstraintValidator<ExistFood, Long> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true; // null 허용

        boolean exists = foodRepository.existsById(value);
        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }
        return exists;
    }
}
