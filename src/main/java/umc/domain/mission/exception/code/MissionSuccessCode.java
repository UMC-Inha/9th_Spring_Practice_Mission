package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

@AllArgsConstructor
@Getter
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션을 생성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
