package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.GetChallengeMissionResDTO;
import umc.domain.mission.service.MemberMissionCommandService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class GetChallengeMissionController {
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{memberId}/missions")
    public ApiResponse<GetChallengeMissionResDTO> challengeMission(
            @PathVariable Long memberId,
            @RequestBody MissionReqDTO.GetChallengeMissionReqDTO request
            ){
        GetChallengeMissionResDTO result = memberMissionCommandService.getChallengeMission(memberId,request.getMissionId());

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);


    }
}
