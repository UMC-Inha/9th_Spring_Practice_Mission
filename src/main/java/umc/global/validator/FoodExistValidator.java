package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.domain.food.exception.code.FoodErrorCode;
import umc.domain.food.repository.FoodRepository;
import umc.global.annotation.ExistFoods;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Long>> {
//검증 대상이 List<Long>임을 명시
//@ExistFoods라는 딱지가 붙었을 때 이 검사관이 작동하도록 연결합니다.
    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        //values -> @ExistFoods가 붙은 필드의 실제 값(음식 ID 리스트)이 넘어온다.
        //context: 오류 메세지를 제어할 수 있는 도구
        boolean isValid = values.stream()
                .allMatch(value -> foodRepository.existsById(value));
        //allMatch : 리스트의 모든 요소가 existsById 조건을 만족하는지 확인
        //리스트의 ID중 단 하나라도 DB에 존재하지 않으면 allMatch는 false를 반환

        if(!isValid){
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }
        return isValid;
    }


}
