package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND_MEMBER(
            HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "존재하지 않는 회원입니다."),

    NOT_OWNER(
            HttpStatus.FORBIDDEN,
            "MEMBER403_1",
            "해당 회원은 OWNER 권한이 아닙니다."),

    INVALID_PASSWORD(
            HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "비밀번호가 올바르지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
