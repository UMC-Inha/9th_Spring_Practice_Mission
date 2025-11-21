package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.global.validator.FoodExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
//이 어노테이션을 어디에 붙일 수 있는지 범위 지정(클래스의 필드 등)
@Retention(RetentionPolicy.RUNTIME)
//이 어노테이션의 정보를 언제까지 유지할지 설정.
public @interface ExistFoods {
    //여기서 디폴트 메세지를 설정
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
