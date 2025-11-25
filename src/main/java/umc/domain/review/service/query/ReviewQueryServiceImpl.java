package umc.domain.review.service.query;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> searchReviewByMember(Long memberId, String storeName, BigDecimal starRating) {
        return reviewRepository.searchReviewByMemberId(memberId, storeName, starRating);
    }
}
