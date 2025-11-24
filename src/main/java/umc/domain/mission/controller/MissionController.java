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

    //지역명으로 미션 조회(특정 지역의 미션 조회)
    @GetMapping
    public ApiResponse<MissionResDTO.MissionListResult> findByLocation(
            @RequestParam String locationName,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        MissionResDTO.MissionListResult result = missionQueryService.findByLocationName(locationName, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //특정 가게에 미션 생성
    @PostMapping("/stores/{storeId}")
    public ApiResponse<MissionResDTO.MissionDetailDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.MissionCreateReq req
    ) {
        MissionResDTO.MissionDetailDTO result = missionCreateService.createMission(storeId, req);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //특정 가게의 미션 목록 조회
    @GetMapping("/stores/{storeId}")
    public ApiResponse<MissionResDTO.MissionListResult> getStoreMissions(
            @PathVariable Long storeId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        MissionResDTO.MissionListResult result = missionQueryService.findByStoreId(storeId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //회원이 특정 미션에 도전(멤버 미션에 미션 추가)
    @PostMapping("/members/{memberId}")
    public ApiResponse<MissionResDTO.GetChallengeMissionResDTO> challengeMission(
            @PathVariable Long memberId,
            @RequestBody MissionReqDTO.GetChallengeMissionReqDTO request
    ) {
        MissionResDTO.GetChallengeMissionResDTO result =
                memberMissionCommandService.getChallengeMission(memberId, request.getMissionId());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //회원이 진행중인 미션 목록 조회. 페이징 지원
    @GetMapping("/members/{memberId}/ongoing")
    public ApiResponse<MissionResDTO.OnGoingMissionListResult> getOnGoingMissions(
            @PathVariable Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        MissionResDTO.OnGoingMissionListResult result = memberMissionQueryService.getOnGoingMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 회원의 완료한 미션 목록 조회. 페이징 지원
    @GetMapping("/members/{memberId}/completed")
    public ApiResponse<MissionResDTO.CompletedMissionListResult> getCompletedMissions(
            @PathVariable Long memberId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        MissionResDTO.CompletedMissionListResult result = memberMissionQueryService.getCompletedMissions(memberId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
