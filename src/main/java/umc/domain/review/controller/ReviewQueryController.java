package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.MyReviewQuery;
import umc.domain.review.dto.ReviewCard;
import umc.domain.review.service.ReviewQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public Page<ReviewCard> findMyReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Float star,
            @RequestParam(required = false) Float minStar,
            @RequestParam(required = false) Float maxStar,
            @RequestParam(required = false) String sort,
            Pageable pageable
    ) {
        Long currentMemberId = getCurrentMemberId();
        MyReviewQuery query = new MyReviewQuery(storeId, star, minStar, maxStar, sort);
        return reviewQueryService.getMyReviews(currentMemberId, query, pageable);
    }

    private Long getCurrentMemberId() {
        return 1L;
    }
}
