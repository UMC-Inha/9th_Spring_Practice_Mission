package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.code.ReviewSuccessCode;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.service.ReviewQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;


@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
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
}
