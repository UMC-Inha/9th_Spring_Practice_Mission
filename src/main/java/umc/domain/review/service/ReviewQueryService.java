package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.review.dto.MyReviewQuery;
import umc.domain.review.dto.ReviewCard;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final ReviewRepository reviewRepo;

    public Page<ReviewCard> getMyReviews(Long currentMemberId, MyReviewQuery query, Pageable pageable) {
        return reviewRepo.findMyReviews(currentMemberId, query, pageable);
    }
}
