package umc.domain.review.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.service.query.ReviewQueryServiceImpl;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryServiceImpl reviewQueryService;

    @GetMapping("/{id}/reviews")
    public ApiResponse<List<ReviewDto>> searchReviewByMember(
            @PathVariable Long id,
            @RequestParam String storeName,
            @RequestParam BigDecimal starRating
    ) {
        List<ReviewDto> reviews = reviewQueryService.searchReviewByMember(id, storeName, starRating);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviews
        );
    }
}
