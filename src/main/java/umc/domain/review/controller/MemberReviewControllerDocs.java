package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import umc.domain.review.dto.MemberReviewResDTO.ReviewPreViewListDTO;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;

public interface MemberReviewControllerDocs {

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = """
                특정 멤버가 작성한 리뷰 목록을 페이징하여 조회합니다.
                
                - 리뷰 간단 정보(score, body, photoUrls, createdAt)
                - 관리자/사장님 답글(reviewReply) 포함
                - Page 기반 페이징
                """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 목록 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "멤버를 찾을 수 없음"),
    })
    ApiResponse<ReviewPreViewListDTO> getMyReviews(Long memberId, @ValidPage Integer page);

}
