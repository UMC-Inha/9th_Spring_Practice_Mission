package umc.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@AllArgsConstructor
@Getter
public enum StoreSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "STORE201_1","성공적으로 가게를 생성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
