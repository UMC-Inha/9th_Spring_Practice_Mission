package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import umc.global.validator.FoodExistValidator;

@Documented // 사용자 정의 어노테이션
@Constraint(validatedBy = FoodExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) // 어노테이션 적용 범위
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 생명 주기
public @interface ExistFoods {
    String message() default "해당 음식이 존재하지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
