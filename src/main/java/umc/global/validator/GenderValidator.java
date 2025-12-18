package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import umc.domain.member.entity.MemberGender;
import umc.global.annotation.ValidGender;

public class GenderValidator implements ConstraintValidator<ValidGender, MemberGender> {

    private Set<MemberGender> allowedValues;

    @Override
    public void initialize(ValidGender constraintAnnotation) {
        allowedValues = EnumSet.allOf(MemberGender.class);
    }

    @Override
    public boolean isValid(MemberGender value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 필수면 false로 바꾸거나 @NotNull 추가
        }

        boolean valid = allowedValues.contains(value);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "성별은 " + allowedValues.stream()
                            .map(Enum::name)
                            .collect(Collectors.joining(", "))
                            + " 중 하나여야 합니다."
            ).addConstraintViolation();
        }

        return valid;
    }
}
