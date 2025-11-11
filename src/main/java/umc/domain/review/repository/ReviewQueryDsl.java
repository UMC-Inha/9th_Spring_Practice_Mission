package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {

    //검색 API
    List<Review> searchReview(
            Predicate predicate
    ); //워크북 코드

    //과제 코드
    List<Review> findMyReviewsFiltered(Long userId, Long storeId, Integer starFloor);
}
