package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewCreateDTO;
import com.example.umc9th.domain.review.service.ReviewCreateService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewCreateController {

    private ReviewCreateService reviewCreateService;

    @PostMapping("/{storeId}")
    public ApiResponse<Long> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewCreateDTO reviewCreateDTO
    ) {
        ReviewCreateDTO dto = new ReviewCreateDTO(
                storeId,
                reviewCreateDTO.getStar(),
                reviewCreateDTO.getContent(),
                reviewCreateDTO.getPhoto_url()
        );
        Long result = reviewCreateService.createReview(dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
