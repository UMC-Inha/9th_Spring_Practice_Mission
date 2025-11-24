package umc.domain.mission.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "존재하지 않는 회원입니다."
    ),

    ONGOING_MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "진행 중인 미션이 없습니다."
    ),

    COMPLETED_MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_3",
            "완료된 미션이 없습니다."
    ),
    MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_4",
            "미션이 존재하지 않습니다."
    ),
    MEMBER_MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_5",
            "회원-미션 매핑이 존재하지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
