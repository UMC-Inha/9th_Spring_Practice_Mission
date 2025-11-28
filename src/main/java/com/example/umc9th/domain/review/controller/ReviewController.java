package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.annotation.PageCheck;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/api/reviewList")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviewList(
            @PageCheck @RequestParam (name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.getMyReview(page)
        );
    }



    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 주니 (개발중)",
            description = "특정가게의 리뷰를 모두 조회"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code,reviewQueryService.findReview(storeName, page));
    }

    /*
    @GetMapping("/review/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.searchReview(query, type)
        ).getResult();
    }
     */


    /*
    @GetMapping("/review")
    public ResponseEntity<ApiResponse<List<ReviewResDTO.ReviewDTO>>> filterReviews(
            @RequestParam("storeId") Long storeId,
            @RequestParam(value = "star", required = false) Float star) { // 선택 파라미터

            List<ReviewResDTO.ReviewDTO> response = reviewQueryService.getReviews(storeId, star);
            return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
     */
}
