package umc.domain.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.service.command.StoreCommandService;
import umc.global.annotation.ExistStore;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/stores")
public class StoreController {

    private final ReviewService reviewService;
    private final StoreCommandService storeCommandService;
    private final MissionService missionService;
    //가게추가
    @PostMapping("")
    public ApiResponse<StoreResDTO.Create> createStore(
            @RequestBody StoreReqDTO.StoreCreate dto
    ) {

        StoreResDTO.Create result = storeCommandService.createStore(dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //리뷰추가
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Detail> createReview(
            @ExistStore @PathVariable ("storeId") Long storeId,
            @RequestBody ReviewReqDTO.ReviewCreate dto
    ) {

        ReviewResDTO.Detail result = reviewService.createReview(dto, storeId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }





    //미션추가
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.SimpleMissionDTO> createMission(
            @PathVariable @ExistStore Long storeId,
            @RequestBody MissionReqDTO.CreateMission dto
    ){
        MissionResDTO.SimpleMissionDTO result = missionService.createMission(storeId, dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //가게의 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") @ValidPage int page,
            @Positive @RequestParam(defaultValue = "10") int size
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewService.findStoreReview(null,storeId, null, page, size));
    }

    //가게의 미션 목록 조회
    @GetMapping("/{storeId}/missions")
    @Operation(
            summary = "특정 가게의 모든 미션 조회 API",
            description = "특정 가게의 모든 미션을 조회합니다. 페이지네이션으로 제공"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam (defaultValue = "10") Integer size
    ){
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        MissionResDTO.MissionPreviewListDTO result = missionService.getMissionPreviewList(storeId, page, size);
        return ApiResponse.onSuccess(code, result);
    }
}
