package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.code.MemberMissionSuccessCode;
import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.domain.member.service.command.MemberMissionCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/member-missions")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<MemberMissionResDTO.createResDTO>> addMission(
            @RequestBody MemberMissionReqDTO.createReqDTO dto
    ) {
        MemberMissionResDTO.createResDTO result = memberMissionCommandService.addMission(dto);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberMissionSuccessCode.MISSION_ADD_SUCCESS, result)
        );
    }
}
