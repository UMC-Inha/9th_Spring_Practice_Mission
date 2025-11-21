package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.global.annotation.CheckPage;
import umc.global.apiPayload.code.GeneralErrorCode;

public class CheckPageValidator implements ConstraintValidator<CheckPage,Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null || value < 1){
            context.disableDefaultConstraintViolation();//기본 메시지 끄기
            //커스텀 에러 메세지 설정
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
