package umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.service.command.ReviewCommandService;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/search") //워크북 실습용 API
    public ApiResponse<List<ReviewResDTO.ReviewPreviewDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.searchReview(query, type)
        );
    }


//과제(미션)용 API
@GetMapping("/my")
public ApiResponse<List<ReviewResDTO.ReviewPreviewDTO>>getMyReviews(
        @RequestParam(name = "userId")Long userId,
        @RequestParam(name = "storeId", required = false) Long storeId,
        @RequestParam(name = "starFloor", required = false) Integer starFloor
) {
    return ApiResponse.onSuccess(
            GeneralSuccessCode.OK,
            reviewQueryService.getMyReviews(userId, storeId, starFloor)
    );
}

//리뷰 추가 API
    @PostMapping("/")
    public ApiResponse<ReviewResDTO.AddReviewResDTO>addReview(
            @RequestBody @Valid ReviewReqDTO.AddReviewDTO req
            ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewCommandService.addReview(req)
        );
    }

}


