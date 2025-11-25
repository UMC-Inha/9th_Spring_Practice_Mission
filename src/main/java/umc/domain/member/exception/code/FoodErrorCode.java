package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND_FOOD(HttpStatus.NOT_FOUND, "FOOD404_1", "존재하지 않는 음식입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
