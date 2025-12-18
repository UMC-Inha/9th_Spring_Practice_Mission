package umc.domain.review.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.MemberReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.query.MemberReviewQueryService;
import umc.domain.review.service.query.ReviewQueryService;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Validated
public class MemberReviewController implements MemberReviewControllerDocs{

    private final MemberReviewQueryService memberReviewQueryService;

    @GetMapping("/{memberId}/reviews")
    @Override
    public ApiResponse<MemberReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam @ValidPage Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;

        return ApiResponse.onSuccess(code, memberReviewQueryService.findReview(memberId, page));
    }


}
