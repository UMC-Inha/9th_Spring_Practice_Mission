package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.service.ReviewCommandService;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import static umc.domain.review.dto.req.ReviewReqDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewCommandService reviewCommandService;

    //
    @PostMapping("/me")
    public ApiResponse<Page<ReviewResDTO.MyReviewResDTO>> getMyReviews(
            @RequestParam("member_id") Long memberId, // 실제 인증 객체 타입에 맞게 수정
            @RequestBody MyReviewReqDTO reqDTO,
            Pageable pageable
    ) {

        Page<ReviewResDTO.MyReviewResDTO> result = reviewService.getMyReviews(memberId, reqDTO, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    @PostMapping("/write/{memberId}/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewInfo> writeReview(
            @PathVariable Long memberId,
            @PathVariable Long storeId,
            @RequestBody CreateReview req
    ) {
        ReviewResDTO.ReviewInfo result =
                reviewCommandService.writeReview(memberId, storeId, req);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}
