package umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.review.dto.MyReviewQuery;
import umc.domain.review.dto.ReviewCard;

public interface ReviewQueryDsl {
    Page<ReviewCard> findMyReviews(Long memberId, MyReviewQuery query, Pageable pageable);
}
