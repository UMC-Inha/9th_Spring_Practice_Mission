package umc.domain.mission.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_SEARCH_SUCCESS(HttpStatus.OK, "MISSION200_1", "미션 조회 성공"),
    MISSION_CREATE_SUCCESS(HttpStatus.CREATED, "MISSION201_1", "미션 생성 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
