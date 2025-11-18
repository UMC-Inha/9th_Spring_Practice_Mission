package umc.domain.test.converter;

import umc.domain.test.dto.res.TestResDTO;

public class TestConverter {

    //객체 -> DTO
    public static TestResDTO.Testing toTestingDTO(String testing){
        return TestResDTO.Testing.builder()
                .testing(testing)
                .build();
        //.builder()는 빌더 객체를 만드는 메서드
        //.build()는 최종 객체를 반환

        //builder생성 -> 값 세팅 -> 객체 생성 및 반환
    }

    //객체 -> DTO
    public static TestResDTO.Exception toExceptionDTO(String testing){
        return TestResDTO.Exception.builder()
                .testString(testing).build();
    }
}
