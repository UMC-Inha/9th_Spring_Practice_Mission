package umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements  BaseErrorCode{
    // 400 BAD REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST,
            "COMMON400_2",
            "요청 파라미터가 유효하지 않습니다."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,
            "COMMON400_3",
            "요청 값이 형식에 맞지 않습니다."),

    // 401 Unauthorized
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "COMMON401_1",
            "인증이 필요합니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON403_1",
            "접근 권한이 없습니다."),

    // 404 NOT FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "요청한 리소스를 찾을 수 없습니다."),

    // 405 METHOD NOT ALLOWED
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,
            "COMMON405_1",
            "지원하지 않는 HTTP 메서드입니다."),

    // 409 Conflict
    CONFLICT(HttpStatus.CONFLICT,
            "COMMON409_1",
            "요청이 현재 리소스 상태와 충돌합니다."),

    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
            "COMMON415_1",
            "지원하지 않는 미디어 타입입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500_1",
            "예기치 않은 서버 에러가 발생했습니다."),

    //  503 Service Unavailable
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE,
            "COMMON503_1",
            "현재 서비스를 이용할 수 없습니다."),
    ;
    private final HttpStatus  status;
    private final String code;
    private final String message;
}
