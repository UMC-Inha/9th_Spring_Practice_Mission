package umc.domain.review.repository;

import java.math.BigDecimal;
import java.util.List;
import umc.domain.review.dto.ReviewDto;

public interface ReviewQueryDsl {

    List<ReviewDto> searchReviewByMemberId(
            Long memberId,
            String storeName,
            BigDecimal starRating
    );
}
