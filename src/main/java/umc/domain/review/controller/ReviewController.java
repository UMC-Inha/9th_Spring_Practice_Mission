package umc.domain.review.controller;

import static umc.domain.review.dto.ReviewResDTO.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.command.ReviewCommandService;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController implements ReviewControllerDocs{

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/search")
    public ApiResponse<List<SearchDTO>> searchReview(
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.SearchDTO request) {

        List<SearchDTO> result = reviewQueryService.searchReview(memberId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    @PostMapping
    public ApiResponse<Void> createReview(
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.CreateDTO request) {
        reviewCommandService.createReview(memberId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED);
    }

}
