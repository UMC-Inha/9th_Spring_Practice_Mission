package umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.review.entity.Review;

public interface ReviewRepositoryCustom {

    Page<Review> findMyReviews(Long memberId,
                               Long storeId, Integer rateFilter,
                               Boolean sortByStore, Boolean sortByRate,Pageable pageable
    );
}
