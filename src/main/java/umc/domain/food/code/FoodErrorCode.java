package umc.domain.food.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD404_1", "해당 음식이 존재하지 않습니다");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
