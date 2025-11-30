package umc.domain.member.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MM404_1", "해당 회원을 찾을 수 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MM404_2", "해당 미션을 찾을 수 없습니다."),
    MEMBER_MISSION_ALREADY_EXISTS(HttpStatus.CONFLICT, "MM409_1", "회원에게 이미 등록된 미션입니다."),
    MEMBER_MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MM400_1", "이미 완료된 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
