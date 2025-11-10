package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.service.ReviewQueryService;


@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public ResponseEntity<Page<ReviewResponseDTO>> searchReview(
            @RequestParam Long memberId,              // ✅ 추가됨
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query,
            Pageable pageable
    ){
        Page<ReviewResponseDTO> result = reviewQueryService.searchReview(memberId, type, query, pageable);
        return ResponseEntity.ok(result);
    }
}
