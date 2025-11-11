package umc.domain.review.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.service.ReviewQueryService;

@RestController
public class ReviewController {

    private ReviewQueryService reviewQueryService;

    public List<ReviewDto> searchReviewByMember(
            @RequestParam Long id,
            @RequestParam String storeName,
            @RequestParam BigDecimal starRating
    ) {

        return reviewQueryService.searchReviewByMember(id, storeName, starRating);
    }
}
