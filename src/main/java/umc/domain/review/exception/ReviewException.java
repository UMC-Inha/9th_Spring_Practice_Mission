package umc.domain.review.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code){
        super(code);
        //GeneralException(부모)의 생성자에 code를 넘겨줌
    }
}
