package umc.domain.member.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
