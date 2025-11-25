package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import umc.global.validator.GenderValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface ValidGender {
    String message() default "유효하지 않은 성별입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}