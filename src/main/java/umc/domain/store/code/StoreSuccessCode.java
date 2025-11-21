package umc.domain.store.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_CREATE_SUCCESS(
            HttpStatus.CREATED,
            "STORE201_1",
            "가게가 성공적으로 생성되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
