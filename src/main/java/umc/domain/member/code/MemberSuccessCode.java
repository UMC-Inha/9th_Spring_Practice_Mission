package umc.domain.member.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    MEMBER_LIST_SUCCESS(HttpStatus.OK, "MEMBER200_1", "유저 조회 성공"),
    MEMBER_SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원 가입 성공"),
    MEMBER_LOGIN_FOUND(HttpStatus.FOUND, "MEMBER302_1", "회원 로그인 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
