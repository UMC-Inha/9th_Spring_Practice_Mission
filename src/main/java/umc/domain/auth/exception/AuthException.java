package umc.domain.auth.exception;

import umc.global.apiPayload.Exception.GeneralException;
import umc.global.apiPayload.code.BaseErrorCode;

public class AuthException extends GeneralException {
    public AuthException(BaseErrorCode code) {
        super(code);
    }
}
