package umc.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import umc.global.validator.StoreTypeValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StoreTypeValidator.class)
public @interface ValidStoreType {
    String message() default "유효하지 않은 가게 타입입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}