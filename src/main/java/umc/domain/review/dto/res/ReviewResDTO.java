package umc.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    //이렇게 따로 빼둔 이유는 그룹핑 목적!
    //프로젝트 하다보면 리뷰와 관련된 DTO가 점점 많아질텐데, 이걸 파일로 전부 만들면 지저분해져
    //그래서 Response와 관련된 Review DTO들을 하나의 클래스로 묶어버리는 방식
    @Builder
    @Getter
    public static class ReviewPreviewWorkbookDTO {
        private Long reviewId;
        private String storeName;
        private Float rating;
        private String content;
        private LocalDate createdAt;
    }

    @Builder
    public record AddReviewResDTO(
            Long reviewId,
            Long storeId,
            String content,
            Float rating,
            LocalDateTime createdAt
    ){}

    @Builder
    public record ReviewPreViewListDTO(
       List<ReviewPreviewDTO> reviewList,
       Integer listSize,
       Integer totalPage,
       Long totalElements,
       Boolean isFirst,
       Boolean isLast
    ){}

    @Builder
    public record ReviewPreviewDTO(
            String ownerNickname,
            Float rating,
            String content,
            LocalDate createdAt
    ){}

    //내 리뷰 목록(페이징 정보 포함)
    @Builder
    public record MyReviewPreviewListDTO(
            List<ReviewPreviewWorkbookDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}




}
