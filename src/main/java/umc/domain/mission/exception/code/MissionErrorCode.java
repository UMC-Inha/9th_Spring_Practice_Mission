package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾지 못했습니다."),

    MISSION_ALREADY_CHALLENGING(HttpStatus.CONFLICT,
            "MISSION409_1",
            "이미 도전 중인 미션입니다.");
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
