package umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageLimitErrorCode implements BaseErrorCode{


    PAGE_LIMIT_ERROR_CODE(HttpStatus.BAD_REQUEST,
            "COMMON400_5",
            "페이지 사이즈는 1 이상이어야 합니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
