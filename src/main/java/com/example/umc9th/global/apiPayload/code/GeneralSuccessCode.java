package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    // 200 OK, 201 Created, 204 No content
    OK(HttpStatus.OK, "COMMON200_1", "요청이 성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201_1", "요청이 성공했으며 리소스가 생성되었습니다"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON204_1", "요청이 성공적으로 수행되었지만 응답에 포함할 내용이 없습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
