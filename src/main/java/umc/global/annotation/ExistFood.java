package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.global.validator.FoodExistSingleValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodExistSingleValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFood {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}