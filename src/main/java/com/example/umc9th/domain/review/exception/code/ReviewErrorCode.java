package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVEIW_COMMON404_1",
                    "요청한 리뷰를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "REVIEW_COMMON500_1",
            "예기치 않은 서버 에러가 발생했습니다."),
    REVIEW_BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}