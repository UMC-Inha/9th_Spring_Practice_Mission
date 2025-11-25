package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import umc.domain.review.dto.ReviewResDTO.ReviewPreviewListDTO;
import umc.global.apiPayload.ApiResponse;

public interface ReviewControllerDocs {

    @Operation( // Swagger 전용 Docs
            summary = "가게의 리뷰 목록 조회 API By 여니",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지 네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패"),
    })
    ApiResponse<ReviewPreviewListDTO> getReviews(String storeName, Integer page);
}
