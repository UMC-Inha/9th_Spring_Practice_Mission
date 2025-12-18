package umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import umc.domain.mission.dto.MissionResDTO.SearchListDTO;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;

public interface MissionControllerDocs {

    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = """
            지정된 가게(storeId)에 등록된 미션을 페이지 단위로 조회합니다.
            """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 목록 조회 성공"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "page 값 검증 실패 (VALID_FAIL)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "해당 storeId를 찾을 수 없음"
            )
    })
    ApiResponse<SearchListDTO> getMission(
            Long storeId, @ValidPage Integer page
    );


}
