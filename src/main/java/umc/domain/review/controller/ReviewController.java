package umc.domain.review.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.CreateReviewDto;
import umc.domain.review.dto.FindMyReviewDto;
import umc.domain.review.dto.ReqFindMyReviewDto;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.annotation.PageLimit;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;


@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs{

    private final ReviewService reviewService;



    @GetMapping("/{memberId}")
    public ApiResponse<List<FindMyReviewDto>> findMyReviews(
            @PathVariable("memberId") @NotNull(message = "필수 값입니다") Long memberId,
            @RequestBody @Valid ReqFindMyReviewDto reqFindMyReviewDto,
            @PageLimit @PageableDefault(page=0, size=10) Pageable pageable
    ){

        Page<Review> myReview = reviewService.findMyReviews(memberId,
                reqFindMyReviewDto,pageable.getPageNumber(), pageable.getPageSize());


        Page<FindMyReviewDto> reviewDtoPages = myReview.map(ReviewConverter::toReviewDTO);

        ReviewSuccessCode code = ReviewSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, reviewDtoPages.getContent()
        );

    }


    @PostMapping("/{memberId}/{storeId}")
    public ApiResponse<?> createReview(
            @NotBlank @PathVariable("memberId") Long memberId,
            @NotBlank @PathVariable("storeId") Long storeId,
            @RequestBody @Valid CreateReviewDto createReviewDto
            ){


        Long reviewId = reviewService.createReview(memberId, storeId, createReviewDto.getRate(),
                createReviewDto.getDescription(), createReviewDto.getPhoto());

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, reviewId
        );

    }



}
