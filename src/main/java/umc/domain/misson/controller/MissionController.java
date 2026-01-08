package umc.domain.misson.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.exception.membermission.code.MemberMissionSuccessCode;
import umc.domain.misson.dto.MissionResDTO.MissionPreviewDTO;
import umc.domain.misson.dto.MissionResDTO.MissionPreviewListDTO;
import umc.domain.misson.exception.code.MissionSuccessCode;
import umc.domain.misson.service.command.MissionCommandService;
import umc.domain.misson.service.query.MissionQueryService;
import umc.global.annotation.PageParam;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @GetMapping("/missions")
    @Override
    public ApiResponse<MissionPreviewListDTO> getMissionsByStore(
            @RequestParam @NotBlank String storeName,
            @RequestParam @PageParam Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findMissionsByStore(storeName, page));
    }

    @GetMapping("/{memberId}/missions/ongoing")
    @Override
    public ApiResponse<MissionPreviewListDTO> getOngoingMissions(
            @PathVariable @NotBlank Long memberId,
            @RequestParam @PageParam Integer page
    ) {
        MemberMissionSuccessCode code = MemberMissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findOngoingMissions(memberId, page));
    }

    @PostMapping("/{memberId}/{missionId}/completed")
    @Override
    public ApiResponse<MissionPreviewDTO> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        MissionSuccessCode code = MissionSuccessCode.UPDATED_STATUS;
        return ApiResponse.onSuccess(code, missionCommandService.updateMissionStatus(memberId, missionId));
    }
}
