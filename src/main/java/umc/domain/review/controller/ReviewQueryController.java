package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.req.MyReviewReqDTO;
import umc.domain.review.dto.res.MyReviewResDTO;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewQueryController {

    private final ReviewService reviewService;

    @GetMapping("/me")
    public ApiResponse<Page<MyReviewResDTO>> getMyReviews(
            @RequestParam("member_id") Long memberId, // 실제 인증 객체 타입에 맞게 수정
            @RequestBody MyReviewReqDTO reqDTO,
            Pageable pageable
    ) {

        Page<MyReviewResDTO> result = reviewService.getMyReviews(memberId, reqDTO, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
