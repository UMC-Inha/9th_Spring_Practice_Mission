package umc.domain.review.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.CreateReviewDto;
import umc.domain.review.dto.FindMyReviewDto;
import umc.domain.review.entity.Review;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;


@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;



    @GetMapping("/{memberId}")
    public ApiResponse<Page<FindMyReviewDto>> findMyReview(
            @PathVariable("memberId")
            @NotNull(message = "필수 값입니다")
            Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false)
            @Min(value = 1, message = "rate는 1 이상이어야 합니다")
            @Max(value = 5, message = "rate는 5 이하여야 합니다")
            Integer rate,
            @RequestParam(defaultValue = "false") Boolean sortByStore,
            @RequestParam(defaultValue = "false") Boolean sortByRate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        Page<Review> myReview = reviewService.findMyReview(memberId,
                storeId,rate,sortByStore,sortByRate,page, size);


        Page<FindMyReviewDto> reviewDtoPage = myReview.map(ReviewConverter::toReviewDTO);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, reviewDtoPage
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
