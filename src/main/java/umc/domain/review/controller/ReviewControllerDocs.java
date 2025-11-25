package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import umc.domain.review.dto.FindMyReviewDto;
import umc.domain.review.dto.ReqFindMyReviewDto;
import umc.global.apiPayload.ApiResponse;

import java.util.List;

public interface ReviewControllerDocs {

    @Operation(
            summary = "내가 적은 리뷰 조회",
            description = "가계나 별점별로 필터링 할 수 있다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")

    })
    ApiResponse<List<FindMyReviewDto>> findMyReviews(@PathVariable("memberId") @NotNull(message = "필수 값입니다") Long memberId,
                                                    @RequestBody @Valid ReqFindMyReviewDto reqFindMyReviewDto,
                                                    @PageableDefault(page=0, size=10) Pageable pageable);
}
