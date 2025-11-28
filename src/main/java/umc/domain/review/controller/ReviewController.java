package umc.domain.review.controller;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.query.ReviewQueryServiceImpl;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

    private final ReviewQueryServiceImpl reviewQueryService;

    @GetMapping("/{memberId}/reviews")
    public ApiResponse<List<ReviewDto>> searchReviewByMember(
            @PathVariable Long memberId,
            @RequestParam String storeName,
            @RequestParam BigDecimal starRating
    ) {
        List<ReviewDto> reviews = reviewQueryService.searchReviewByMember(memberId, storeName, starRating);
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviews
        );
    }

    @GetMapping("/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }
}
