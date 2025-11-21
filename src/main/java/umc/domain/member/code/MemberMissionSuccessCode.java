package umc.domain.member.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    MISSION_ADD_SUCCESS(HttpStatus.OK, "MM200_1", "회원 미션 추가 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
