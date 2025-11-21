package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.global.annotation.CheckPage;
import umc.global.apiPayload.ApiResponse;
public interface ReviewControllerDocs {
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 박콩(개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @Parameters({
            @Parameter(name="storeName", description = "가게 이름"),
            @Parameter(name="page",description = "페이지 번호")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam(name="storeName") String storeName,
            @RequestParam(name="page") Integer page
    );

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "나의 리뷰 목록을 조회합니다. page는 1부터 시작")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "페이지 번호가 1보다 작을 때 발생")
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<ReviewResDTO.MyReviewPreviewListDTO> getMyReviewList(
            @CheckPage @RequestParam(name = "page") Integer page // 커스텀 어노테이션 적용!
    );
}
