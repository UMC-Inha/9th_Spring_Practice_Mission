package umc.domain.mission.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.CreateMissionDto;
import umc.domain.mission.entity.MemberMission;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

    private MissionService missionService;

    @PostMapping("/{memberId}/{missionId}")
    public ApiResponse<?> createMemberMission(
            @NotBlank @PathVariable Long memberId,
            @NotBlank @PathVariable Long missionId
    ){

        Long memberMissionId = missionService.createMemberMission(memberId, missionId);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, memberMissionId
        );
    }



    @PostMapping("/{memberId}/{storeId}")
    public ApiResponse<?> createMission(
            @NotEmpty @PathVariable Long memberId,
            @NotBlank @PathVariable Long storeId,
            @Valid @RequestBody CreateMissionDto createMissionDto
            ){

        Long missionId = missionService.createMission(storeId, createMissionDto);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, missionId
        );
    }
}
