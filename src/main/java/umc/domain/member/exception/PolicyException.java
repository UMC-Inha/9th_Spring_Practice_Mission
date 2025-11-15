package umc.domain.member.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class PolicyException extends GeneralException {
    public PolicyException(BaseErrorCode code) {
        super(code);
    }
}
