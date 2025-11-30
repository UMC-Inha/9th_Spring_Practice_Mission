package umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.code.ReviewSuccessCode;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.dto.res.ReviewResponseDTO;
import umc.domain.review.service.command.ReviewCommandService;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;


@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs{

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/reviews/search")
    public ResponseEntity<ApiResponse<PageResponseDTO<ReviewResponseDTO>>> searchReview(
            @RequestParam Long memberId,              // ✅ 추가됨
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "id",   // Review 엔티티 기준
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ){
        PageResponseDTO<ReviewResponseDTO> response =
                reviewQueryService.searchReview(memberId, type, query, pageable);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_SEARCH_SUCCESS, response)
        );
    }

    @PostMapping("/stores/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateResDTO>> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS,reviewCommandService.createReview(dto))
        );
    }

    @Override
    @GetMapping("/review")
    public ResponseEntity<ApiResponse<PageResponseDTO<ReviewResDTO.ReviewPreViewDTO>>> getReviews(
            @RequestBody @Valid ReviewReqDTO.FindReviewDTO dto
    ){
        String storeName = dto.storeName();
        Integer page = dto.page();

        PageResponseDTO<ReviewResDTO.ReviewPreViewDTO> response =
                reviewQueryService.findReview(storeName, page);
        return ResponseEntity.ok(
                ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_SEARCH_SUCCESS, response)
        );
    }

    @Override
    @GetMapping("/members/{memberId}/reviews")
    public ResponseEntity<ApiResponse<PageResponseDTO<ReviewResDTO.MemberReviewDTO>>> getMemberReviews(
            @PathVariable Long memberId,
            @RequestBody @Valid ReviewReqDTO.FindMemberReviewDTO dto
    ){
        Integer page = dto.page();

        PageResponseDTO<ReviewResDTO.MemberReviewDTO> response =
                reviewQueryService.findMemberReview(memberId, page);
        return ResponseEntity.ok(
                ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_SEARCH_SUCCESS, response)
        );
    }
}
