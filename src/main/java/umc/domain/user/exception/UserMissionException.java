package umc.domain.user.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class UserMissionException extends GeneralException {
    public UserMissionException(BaseErrorCode code) {
        super(code);
    }
}
