package umc.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_CREATED(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 가게를 생성했습니다"),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
