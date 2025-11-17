package umc.global.apiPayload.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
}
