package umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.review.entity.Review;


public interface ReviewQueryDsl {

    Page<Review> searchReview(
            Long memberId, String type, String query, Pageable pageable
    );
}
