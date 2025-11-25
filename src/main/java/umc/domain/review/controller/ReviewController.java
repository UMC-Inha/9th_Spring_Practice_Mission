package umc.domain.review.controller;

import static umc.domain.review.dto.ReviewResDTO.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.command.ReviewCommandService;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping
    public ApiResponse<List<SearchDTO>> searchReview(
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.SearchDTO request) {

        List<SearchDTO> result = reviewQueryService.searchReview(memberId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping
    public ApiResponse<Void> createReview(
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.CreateDTO request) {
        reviewCommandService.createReview(memberId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED);
    }

}
