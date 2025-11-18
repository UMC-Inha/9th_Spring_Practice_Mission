package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    INVALID_STAR_VALUE(HttpStatus.BAD_REQUEST, "REVIEW-4002", "별점은 숫자여야 합니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW-404", "리뷰를 찾을 수 없습니다.");
    //워크북때 배운거 쓰려고 ErrorCode 만들었는데 생각해보니 안쓰는게 맞는거같아서 Service에서 사용 안했습니다

    private final HttpStatus status;
    private final String code;
    private final String message;
}
