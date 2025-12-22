package umc.global.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.global.validator.PageValidator;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageValidator.class)



public @interface ValidPage {
    int value() default 1;
    String message() default "page는 {value} 이상이어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
