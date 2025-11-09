package umc.domain.review.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> searchReviewByMember(Long memberId, String storeName, BigDecimal starRating) {
        return reviewRepository.searchReviewByMemberId(memberId, storeName, starRating);
    }
}
