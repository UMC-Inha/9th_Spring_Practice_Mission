package umc.domain.store.exception.region;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class RegionException extends GeneralException {

    public RegionException(BaseErrorCode code) {
        super(code);
    }
}
