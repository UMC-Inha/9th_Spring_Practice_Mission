package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.global.annotation.PageParam;
import umc.global.apiPayload.code.GeneralErrorCode;

@Component
@RequiredArgsConstructor
public class PageParamValidator implements ConstraintValidator<PageParam, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (page >= 0) {
            isValid = true;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.INVALID_PAGE.getMessage())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
