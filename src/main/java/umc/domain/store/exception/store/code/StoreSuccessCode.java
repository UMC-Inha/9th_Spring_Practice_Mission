package umc.domain.store.exception.store.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "STORE201_1", "상점이 정상적으로 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}