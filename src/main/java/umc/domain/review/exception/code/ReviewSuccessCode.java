package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.FOUND, "REVIEW200_1","리뷰를 찾는데 성공했습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
