package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "성공적으로 리뷰를 생성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
