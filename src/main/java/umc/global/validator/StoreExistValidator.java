package umc.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.domain.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.global.annotation.ExistStore;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        if (storeId == null) {
            return true; // null 허용
        }

        boolean exists = storeRepository.existsById(storeId);

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StoreErrorCode.STORE_NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }

        return exists;
    }
}

