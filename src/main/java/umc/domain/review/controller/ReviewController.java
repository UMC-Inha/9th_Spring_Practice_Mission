package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

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
}


