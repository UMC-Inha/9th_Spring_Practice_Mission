package umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import umc.domain.mission.dto.MissionDto;
import umc.global.annotation.PageLimit;
import umc.global.apiPayload.ApiResponse;

import java.util.List;

public interface MissionControllerDocs {

    @Operation(
            summary = "가게에 속한 리뷰 조회"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "실패")

    })
    public ApiResponse<List<MissionDto>> findStoreMission(
            @NotNull(message = "필수 값입니다") Long memberId,
            @NotNull(message = "필수 값입니다") Long storeId,
            @PageLimit @PageableDefault(page=0, size=10) Pageable pageable
    );



    @Operation(
            summary = "내가 진행 중인 미션 조회"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "실패")

    })
    public ApiResponse<List<MissionDto>> findMyInProgressMission(
            @NotNull(message = "필수 값입니다") Long memberId,
            @PageLimit @PageableDefault(page=0, size=10) Pageable pageable
    );
}



