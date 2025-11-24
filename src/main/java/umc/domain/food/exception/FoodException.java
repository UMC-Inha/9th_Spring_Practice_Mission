package umc.domain.food.exception;

import umc.global.apiPayload.Exception.GeneralException;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
