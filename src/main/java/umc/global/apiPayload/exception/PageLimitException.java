package umc.global.apiPayload.exception;

import umc.global.apiPayload.code.BaseErrorCode;

public class PageLimitException extends GeneralException {
    public PageLimitException(BaseErrorCode code) {
        super(code);
    }
}

