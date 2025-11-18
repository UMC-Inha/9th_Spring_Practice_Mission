package umc.domain.review.service.query;

import java.math.BigDecimal;
import java.util.List;
import umc.domain.review.dto.ReviewDto;

public interface ReviewQueryService {

    List<ReviewDto> searchReviewByMember(Long memberId, String storeName, BigDecimal starRating);
}
