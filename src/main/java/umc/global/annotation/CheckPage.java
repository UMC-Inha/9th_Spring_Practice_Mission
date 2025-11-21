package umc.global.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.global.validator.CheckPageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class) //검증기 연결
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "페이지 번호는 1 이상이어야 합니다."; //기본 에러 메세지
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
