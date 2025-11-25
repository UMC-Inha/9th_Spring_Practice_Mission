package umc.domain.member.exception.membermission.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "MEMBER_MISSION201_1",
            "미션 도전을 성공적으로 등록했습니다."),
    FOUND(HttpStatus.OK, "MEMBER_MISSION200_1", "회원이 도전한 미션을 찾았습니다..");;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
