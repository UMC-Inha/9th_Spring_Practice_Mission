package umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.entity.QReview;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> searchReviewByMember(Long id, String storeName, BigDecimal starRating) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (id != null) {
            builder.and(review.member.id.eq(id));
        }
        if (storeName != null) {
            builder.and(review.store.name.eq(storeName));
        }
        if (starRating != null) {
            builder.and(review.starRating.eq(starRating));
        }

        return reviewRepository.searchReviewByMemberId(builder);
    }
}
