package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@AllArgsConstructor
@Getter
public enum MemberMissionSuccessCode implements BaseSuccessCode {
    MISSION_ACCEPTED(HttpStatus.CREATED,
            "MEMBER_MISSION201_1",
            "성공적으로 미션 수락했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
