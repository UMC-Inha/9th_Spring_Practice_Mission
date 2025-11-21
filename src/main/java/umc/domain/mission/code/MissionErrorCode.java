package umc.domain.mission.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 지역에 진행 중인 미션이 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "해당 가게를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
