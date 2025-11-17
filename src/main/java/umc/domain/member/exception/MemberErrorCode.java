package umc.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode  implements BaseErrorCode {

    // 404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "회원이 존재하지 않습니다."),

;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
