package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.exception.code.MemberMissionSuccessCode;
import umc.domain.member.service.common.MemberMissionCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/members/{memberId}/missions")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{missionId}/accept")
    public ApiResponse<Void> acceptMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId){

        memberMissionCommandService.acceptMission(memberId,missionId);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.MISSION_ACCEPTED);
    }
}
