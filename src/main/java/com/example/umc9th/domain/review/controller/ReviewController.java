package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

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

    @GetMapping("/review")
    public ResponseEntity<ApiResponse<List<ReviewResDTO.ReviewDTO>>> filterReviews(
            @RequestParam("storeId") Long storeId,
            @RequestParam(value = "star", required = false) Float star) { // 선택 파라미터

            List<ReviewResDTO.ReviewDTO> response = reviewQueryService.getReviews(storeId, star);
            return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, response));
    }
}
