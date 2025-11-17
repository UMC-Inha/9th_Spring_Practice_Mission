package umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.review.dto.res.MyReviewResDTO;
import umc.domain.review.repository.query.MyReviewQuery;

public interface ReviewQueryDsl {
    Page<MyReviewResDTO> findMyReviews(Long memberId, MyReviewQuery query, Pageable pageable);
}
