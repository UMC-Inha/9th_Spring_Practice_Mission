package umc.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class ReviewResDTO {

    //이렇게 따로 빼둔 이유는 그룹핑 목적!
    //프로젝트 하다보면 리뷰와 관련된 DTO가 점점 많아질텐데, 이걸 파일로 전부 만들면 지저분해져
    //그래서 Response와 관련된 Review DTO들을 하나의 클래스로 묶어버리는 방식
    @Builder
    @Getter
    public static class ReviewPreviewDTO{
        private Long reviewId;
        private String storeName;
        private Integer rating;
        private String content;
        private LocalDate createdAt;
    }


    @Builder
    @Getter
    public static class Exception{
        private String message;
    }

}
