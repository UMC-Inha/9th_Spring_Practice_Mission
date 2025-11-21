package umc.domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserMissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "USERMISSION404_1",
            "해당 유저미션을 찾지 못했습니다."),

    INVALID_STATUS(HttpStatus.BAD_REQUEST,
            "USERMISSION400_1",
            "SUCCESS만 올 수 있습니다."),

    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST,
            "USERMISSION400_2",
            "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
