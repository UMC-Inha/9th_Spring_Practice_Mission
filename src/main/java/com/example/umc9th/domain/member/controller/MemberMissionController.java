package com.example.umc9th.domain.member.controller;
import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.service.query.MemberMissionQueryService;
import com.example.umc9th.global.annotation.PageCheck;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member_mission")
public class MemberMissionController {
    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/api/IN_PROGRESS")
    public ApiResponse<MemberMissionResDTO.MissionByStatusListDTO> getMemberMissionByStatus(
            @PageCheck @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionQueryService.getMissionByStatus(page)
        );
    }
}