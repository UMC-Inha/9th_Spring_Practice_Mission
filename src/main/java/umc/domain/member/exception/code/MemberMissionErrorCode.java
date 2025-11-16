package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum MemberMissionErrorCode implements BaseErrorCode {

    MISSION_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "MEMBER_MISSION409_001", "현재 도전 중인 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
