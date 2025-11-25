package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "REVIEW200_1", "요청하신 리뷰를 찾았습니다."),
    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 정상적으로 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
