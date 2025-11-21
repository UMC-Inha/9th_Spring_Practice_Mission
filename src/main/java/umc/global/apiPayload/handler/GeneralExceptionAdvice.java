package umc.global.apiPayload.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.GeneralErrorCode;
import umc.global.apiPayload.exception.GeneralException;

import java.util.HashMap;
import java.util.Map;

//전역 예외 처리기 역할
//Api들에서 에러가 발생했을때 이 클래스가 대신 잡아서 공통된 형식으로 응답을 보내줄게!!
@RestControllerAdvice
public class GeneralExceptionAdvice {

    //애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
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

    // 컨트롤러 메서드에서 @Valid 어노테이션을 사용하여 DTO의 유효성 검사를 수행
    // MethodArgumentNotValidException 예외가 발생하면 무조건 실행해라
    // 이 예외는 @Valid가 붙은 객체의 검증이 실패할 때 자동으로 발생시키는 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 Map
        Map<String, String> errors = new HashMap<>(); //어떤 필드(Key)가 어떤 메시지(Value)때문에 틀렸는지를 Map형태로 담음
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        //ex.getBindingResult(): ex 객체에서 "검증 결과"가 담긴 BindingResult를 꺼냄
        //.getFieldErrors(): 검증에 실패한 필드들의 목록 가져옴
        //.forEach(): 실패한 필드 목록 하나씩 순회
        //error.getField:검증에 실패한 필드명 반환
        //error.getDefaultMessage(): 해당 필드의 검증 어노테이션에 설정된 오류 메시지 반환

        GeneralErrorCode code = GeneralErrorCode.VALID_FAIL;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);
        //VALID_FAIL의 정보가 ApiResponse의 isSuccess,code,message 필드를 채움

        // 에러 코드, 메시지와 함께 errors를 반환
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    //@Validated 검증 실패 시 발생하는 예외 처리(ConstraintViolationException)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleConstraintViolationException(ConstraintViolationException ex){

        Map<String, String> errors = new HashMap<>(); //어떤 필드가 어떤 메시지때문에 틀렸는지 Map형태로 담음
        ex.getConstraintViolations().forEach(violation -> {

            String path = violation.getPropertyPath().toString();
            String fieldName = path.substring(path.lastIndexOf('.') + 1); // 결과: page

            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        GeneralErrorCode code = GeneralErrorCode.BAD_REQUEST;
        ApiResponse<Map<String,String>> errorResponse = ApiResponse.onFailure(code, errors);
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }



}
