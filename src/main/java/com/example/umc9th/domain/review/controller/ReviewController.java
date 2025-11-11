package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
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
        List<Review> result = reviewQueryService.searchReview(query,type);
        return result;
    }

    @GetMapping("/review")
    public List<ReviewDTO> filterReviews(
            @RequestParam("storeId") Long storeId,
            @RequestParam(value = "star", required = false) Float star) { // 선택 파라미터

            return reviewQueryService.getReviews(storeId, star);
    }
}
