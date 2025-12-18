package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.dto.MemberMissionResDTO;
import umc.domain.member.exception.code.MemberMissionSuccessCode;
import umc.domain.member.service.common.MemberMissionCommandService;
import umc.domain.mission.entity.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.query.MemberMissionService;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/members/{memberId}/missions")
@RequiredArgsConstructor
public class MemberMissionController implements MemberMissionControllerDocs{

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionService memberMissionService;

    @PostMapping("/{missionId}/accept")
    public ApiResponse<Void> acceptMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId){

        memberMissionCommandService.acceptMission(memberId,missionId);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.MISSION_ACCEPTED);
    }

    @GetMapping
    @Override
    public ApiResponse<MemberMissionResDTO.SearchListDTO> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam(required = false) MissionStatus status,
            @RequestParam @ValidPage Integer page
    ) {
        var result = memberMissionService.getMemberMissions(memberId, status, page);
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
    }
}
