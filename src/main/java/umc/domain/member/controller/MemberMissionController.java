package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.code.MemberMissionSuccessCode;
import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.domain.member.service.command.MemberMissionCommandService;
import umc.domain.member.service.query.MemberMissionQueryService;
import umc.domain.mission.code.MissionSuccessCode;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;

@RestController
@RequestMapping("/member-missions")
@RequiredArgsConstructor
public class MemberMissionController implements MemberMissionControllerDocs {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<MemberMissionResDTO.createResDTO>> addMission(
            @RequestBody MemberMissionReqDTO.createReqDTO dto
    ) {
        MemberMissionResDTO.createResDTO result = memberMissionCommandService.addMission(dto);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberMissionSuccessCode.MISSION_ADD_SUCCESS, result)
        );
    }

    @Override
    @GetMapping("/members/{memberId}/missions/running")
    public ResponseEntity<ApiResponse<PageResponseDTO<MemberMissionResDTO.goingResDTO>>> getRunningMissions(
            @PathVariable Long memberId,
            @RequestBody @Valid MemberMissionReqDTO.FindMemberRunningMissionDTO dto
    ) {
        Integer page = dto.page();

        PageResponseDTO<MemberMissionResDTO.goingResDTO> response =
                memberMissionQueryService.getRunningMissions(memberId, page);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(
                        MissionSuccessCode.MISSION_SEARCH_SUCCESS,
                        response
                )
        );
    }

    @Override
    @Transactional
    @PostMapping("/mission/{memberMissionId}/complete")
    public ResponseEntity<ApiResponse<MemberMissionResDTO.completeResDTO>> completeMission(
            @PathVariable Long memberMissionId
    ) {

        MemberMissionResDTO.completeResDTO response =
                memberMissionCommandService.completeMission(memberMissionId);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberMissionSuccessCode.MISSION_COMPLETE_SUCCESS, response)
        );
    }

}
