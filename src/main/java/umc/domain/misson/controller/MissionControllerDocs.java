package umc.domain.misson.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import umc.domain.misson.dto.MissionResDTO.MissionPreviewDTO;
import umc.domain.misson.dto.MissionResDTO.MissionPreviewListDTO;
import umc.global.annotation.PageParam;
import umc.global.apiPayload.ApiResponse;

public interface MissionControllerDocs {

    @Operation( // Swagger 전용 Docs
            summary = "가게의 미션 목록 조회 API By 여니",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지 네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패"),
    })
    ApiResponse<MissionPreviewListDTO> getMissionsByStore(
            String storeName, @PageParam Integer page
    );


    @Operation( // Swagger 전용 Docs
            summary = "회원의 진행 중인 미션 목록 조회 API By 여니",
            description = "회원의 진행 중인 미션을 모두 조회합니다. 페이지 네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패"),
    })
    ApiResponse<MissionPreviewListDTO> getOngoingMissions(
            Long memberId, @PageParam Integer page
    );

    @Operation( // Swagger 전용 Docs
            summary = "진행 중인 미션 완료로 처리 API By 여니",
            description = "회원의 진행 중인 미션을 완료로 처리합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패"),
    })
    ApiResponse<MissionPreviewDTO> completeMission(
            Long memberId, Long missionId
    );
}
