package umc.global.apiPayload.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.GeneralErrorCode;
import umc.global.apiPayload.exception.GeneralException;

//전역 예외 처리기 역할
//Api들에서 에러가 발생했을때 이 클래스가 대신 잡아서 공통된 형식으로 응답을 보내줄게!!
@RestControllerAdvice
public class GeneralExceptionAdvice {

    //애플리케이션에서 발생하는 커스텀 예외를 처리
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ){
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                        ex.getCode(),
                        null
                ));
    }

    //위 메서드는 Void를 담고 아래는 String 담는 이유
    //위의 경우 result는 null이고 아래는 에러메세지가 result에 담겨서임

    //그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ){
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}
