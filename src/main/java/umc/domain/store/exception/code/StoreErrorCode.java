package umc.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "STORE404_1", "존재하지 않는 가게입니다."),
    NOT_FOUND_DISTRICT(HttpStatus.NOT_FOUND, "STORE404_2", "해당 행정구역을 찾을 수 없습니다."),
    ALREADY_REGISTERED_STORE(HttpStatus.CONFLICT, "STORE409_1", "이미 등록된 가게가 존재합니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
