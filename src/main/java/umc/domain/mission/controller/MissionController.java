package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.service.MemberMissionCommandService;
import umc.domain.mission.service.MemberMissionQueryService;
import umc.domain.mission.service.MissionCreateService;
import umc.domain.mission.service.MissionQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCreateService missionCreateService;
    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    @GetMapping
    public ApiResponse<MissionResDTO.MissionListResult> findByLocation(
            @RequestParam String locationName,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        MissionResDTO.MissionListResult result = missionQueryService.findByLocationName(locationName, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping("/stores/{storeId}")
    public ApiResponse<MissionResDTO.MissionDetailDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.MissionCreateReq req
    ) {
        MissionResDTO.MissionDetailDTO result = missionCreateService.createMission(storeId, req);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping("/members/{memberId}")
    public ApiResponse<MissionResDTO.GetChallengeMissionResDTO> challengeMission(
            @PathVariable Long memberId,
            @RequestBody MissionReqDTO.GetChallengeMissionReqDTO request
    ) {
        MissionResDTO.GetChallengeMissionResDTO result =
                memberMissionCommandService.getChallengeMission(memberId, request.getMissionId());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/members/{memberId}/ongoing")
    public ApiResponse<MissionResDTO.OnGoingMissionListResult> getOnGoingMissions(
            @PathVariable Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        MissionResDTO.OnGoingMissionListResult result = memberMissionQueryService.getOnGoingMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/members/{memberId}/completed")
    public ApiResponse<MissionResDTO.CompletedMissionListResult> getCompletedMissions(
            @PathVariable Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        MissionResDTO.CompletedMissionListResult result = memberMissionQueryService.getCompletedMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
