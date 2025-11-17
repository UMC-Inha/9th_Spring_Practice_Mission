package umc.domain.review.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyReviewResDTO {
    private Long reviewId;
    private Long storeId;
    private String storeName;
    private Integer star;
    private String content;
    private LocalDateTime createdAt;
    private boolean mine;

    public MyReviewResDTO(Long reviewId,
                          Long storeId,
                          String storeName,
                          Integer star,
                          String content,
                          LocalDateTime createdAt,
                          boolean mine) {
        this.reviewId = reviewId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.star = star;
        this.content = content;
        this.createdAt = createdAt;
        this.mine = mine;
    }
}
