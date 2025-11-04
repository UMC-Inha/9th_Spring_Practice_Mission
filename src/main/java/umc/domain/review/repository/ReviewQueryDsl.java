package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import java.util.List;
import umc.domain.review.dto.ReviewDto;

public interface ReviewQueryDsl {

    List<ReviewDto> searchReviewByMemberId(
            Predicate predicate
    );
}
