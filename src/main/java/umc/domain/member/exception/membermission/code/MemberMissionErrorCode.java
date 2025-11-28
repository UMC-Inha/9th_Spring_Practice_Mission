package umc.domain.member.exception.membermission.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION404_1", "해당 미션을 도전하고 있지 않습니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MEMBER_MISSION400_1", "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
