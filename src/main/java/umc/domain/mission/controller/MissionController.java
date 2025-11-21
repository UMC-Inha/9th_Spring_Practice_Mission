package umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.service.command.MissionCommandService;
import umc.domain.mission.service.query.MissionQueryService;
import umc.global.annotation.CheckPage;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/")
    public ApiResponse<MissionResDTO.AddMissionResDTO> addMission(
            @RequestBody @Valid MissionReqDTO.AddMissionReqDTO req
            ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionCommandService.addMission(req)
        );
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회합니다. page는 1부터 시작합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<MissionResDTO.MissionByStoreListResDTO> getMissionByStore(
            @PathVariable Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMissionByStoreListResDTO(storeId, page)
        );

    }
}
