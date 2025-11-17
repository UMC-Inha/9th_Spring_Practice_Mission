package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.service.MemberMissionQueryService;
import umc.global.apiPayload.ApiResponse;

import static umc.domain.mission.dto.res.MemberMissionResDTO.*;
import static umc.global.apiPayload.code.GeneralSuccessCode.*;

@RestController
@RequiredArgsConstructor

public class MemberMissionController {
    private final MemberMissionQueryService memberMissionQueryService;

    @GetMapping("/api/missions/ongoing")
    public ApiResponse<OnGoingMissionListResult> getOnGoingMissions(
            @RequestParam Long memberId,
            @PageableDefault(page=0, size=10) Pageable pageable
    ) {

        OnGoingMissionListResult onGoingMissions = memberMissionQueryService.getOnGoingMissions(memberId, pageable);
        return ApiResponse.onSuccess(OK,    onGoingMissions);
    }

    @GetMapping("/api/missions/completed")
    public ApiResponse<CompletedMissionListResult> getCompletedMissions(
            @RequestParam Long memberId,

            @PageableDefault(page=0, size=10) Pageable pageable

    ) {
        CompletedMissionListResult completedMissions = memberMissionQueryService.getCompletedMissions(memberId, pageable);
        return ApiResponse.onSuccess(OK,completedMissions);
    }
}
