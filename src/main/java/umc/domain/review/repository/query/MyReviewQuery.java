package umc.domain.review.repository.query;

import lombok.Builder;
import lombok.Getter;
import umc.domain.review.service.ReviewSort;

@Getter
@Builder
public class MyReviewQuery {

    private final Long storeId;
    private final Integer minStar;
    private final Integer maxStar;
    private final ReviewSort sortKey;
}
