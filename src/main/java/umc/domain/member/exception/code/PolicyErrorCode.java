package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PolicyErrorCode implements BaseErrorCode {

    REQUIRED_POLICY_NOT_ACCEPTED(
            HttpStatus.BAD_REQUEST,
            "POLICY400_1",
            "필수 약관에 동의하지 않았습니다."
    ),
    NOT_FOUND_POLICY(
            HttpStatus.BAD_REQUEST,
            "MEMBER404_1",
            "존재하지 않는 약관입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
