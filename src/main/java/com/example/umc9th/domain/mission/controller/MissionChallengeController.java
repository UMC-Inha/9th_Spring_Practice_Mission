package com.example.umc9th.domain.mission.controller;


import com.example.umc9th.domain.mission.dto.MissionChallengeDTO;
import com.example.umc9th.domain.mission.dto.MissionCreateDTO;
import com.example.umc9th.domain.mission.service.MissionChallengeService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/missions")
public class MissionChallengeController {

    private final MissionChallengeService missionChallengeService;
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionChallengeDTO> challengeMission (
            @PathVariable Long storeId,
            @PathVariable Long missionId
    ) {
        MissionChallengeDTO result = missionChallengeService.challengeMission(storeId, missionId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
