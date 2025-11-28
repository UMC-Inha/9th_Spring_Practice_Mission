package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.FOUND, "REVIEW_COMMON200_1", "리뷰를 성공적으로 찾았습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
