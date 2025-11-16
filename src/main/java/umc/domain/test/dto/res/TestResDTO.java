package umc.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {
    //객체를 dto로
    @Builder
    @Getter
    public static class Testing{
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception{
        private String testString;
    }
}
