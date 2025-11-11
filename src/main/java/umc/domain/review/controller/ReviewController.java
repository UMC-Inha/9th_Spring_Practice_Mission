package umc.domain.review.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.FindMyReviewDto;
import umc.domain.review.entity.Review;
import umc.domain.review.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{memberId}")
    public List<FindMyReviewDto> findMyReviews(
            @PathVariable("memberId") Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rate,
            @RequestParam(defaultValue = "false") Boolean sortByStore,
            @RequestParam(defaultValue = "false") Boolean sortByRate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        Page<Review> myReview = reviewService.findMyReview(memberId,
                storeId,rate,sortByStore,sortByRate,page, size);

        return myReview.stream()
                .map(r -> new FindMyReviewDto(
                        r.getStore().getName(),
                        r.getRate(),
                        r.getDiscription()
                ) )
                .collect(Collectors.toList());

    }
}
