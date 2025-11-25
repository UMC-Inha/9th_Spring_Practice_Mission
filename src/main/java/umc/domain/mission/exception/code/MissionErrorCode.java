package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND_MISSION(
            HttpStatus.BAD_REQUEST,
            "MISSION404_1",
            "존재하지 않는 미션입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
