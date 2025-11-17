package umc.domain.review.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.review.service.ReviewSort;

@Getter
@NoArgsConstructor
public class MyReviewReqDTO {

    private Long storeId;
    private Integer minStar;
    private Integer maxStar;

    private ReviewSort sortKey;
}
