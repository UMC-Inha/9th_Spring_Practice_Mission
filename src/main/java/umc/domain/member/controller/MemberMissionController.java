package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.membermission.MemberMissionResDTO;
import umc.domain.member.exception.membermission.code.MemberMissionSuccessCode;
import umc.domain.member.service.command.MemberMissionCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/missions/{missionId}/{memberId}")
    public ApiResponse<MemberMissionResDTO.challengeMissionResDTO> challengeMission(
            @PathVariable Long missionId,
            @PathVariable Long memberId
    ) {
        MemberMissionResDTO.challengeMissionResDTO resDTO =
                memberMissionCommandService.challengeMission(missionId, memberId);

        return ApiResponse.onSuccess(MemberMissionSuccessCode.CREATED, resDTO);
    }
}
