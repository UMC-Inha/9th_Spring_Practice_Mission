package umc.domain.misson.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 정상적으로 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}