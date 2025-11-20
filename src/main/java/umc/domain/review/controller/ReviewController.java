package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.command.ReviewCommandService;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController implements ReviewControllerDocs{
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/search") //워크북 실습용 API
    public ApiResponse<List<ReviewResDTO.ReviewPreviewWorkbookDTO>> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.searchReview(query, type)
        );
    }

    //가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code,reviewQueryService.findReview(storeName,page));
    }


//과제(미션)용 API
@GetMapping("/my")
public ApiResponse<List<ReviewResDTO.ReviewPreviewWorkbookDTO>>getMyReviews(
        @RequestParam(name = "userId")Long userId,
        @RequestParam(name = "storeId", required = false) Long storeId,
        @RequestParam(name = "starFloor", required = false) Float starFloor
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


