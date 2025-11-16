package umc.domain.review.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    REVIEW_SEARCH_SUCCESS(HttpStatus.OK, "REVIEW200_1", "리뷰 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
