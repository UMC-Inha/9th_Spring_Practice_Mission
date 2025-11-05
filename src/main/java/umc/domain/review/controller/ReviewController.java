package umc.domain.review.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.service.ReviewQueryService;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{id}/reviews")
    public List<ReviewDto> searchReviewByMember(
            @PathVariable Long id,
            @RequestParam String storeName,
            @RequestParam BigDecimal starRating
    ) {

        return reviewQueryService.searchReviewByMember(id, storeName, starRating);
    }
}
