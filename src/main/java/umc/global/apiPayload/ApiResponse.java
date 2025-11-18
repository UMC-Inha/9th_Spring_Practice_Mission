package umc.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
//JSON 출력 시 필드 순서를 강제한다.
@JsonPropertyOrder({"isSuccess","code","message","result"})
//ApiResponse가 어떤 데이터 타입이든 결과 안에 담을 수 있게 해줌
//result의 타입이 유동적일 수 있도록 하는 장치
public class ApiResponse<T> {

    //Jackson이 JSON을 만들 때 정확히 "isSuccess"라는 이름을 씀
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    //성공한 경우 (result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true,code.getCode(),code.getMessage(),result);
    }

    //실패한 경우 (result 포함)
    //객체 생성 없이 바로 쓸 수 있는 정적 메서드로 만듦
    //static 뒤에 <T>를 붙여야 에러 안나고 이 static메서드 안에서 T라는 타입을 새로 정의해서 쓰겟다라는 뜻
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result){
        return new ApiResponse<>(false, code.getCode(),code.getMessage(),result);
    }
}
