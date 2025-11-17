package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.service.MissionQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor

public class MissionController {

    private final MissionQueryService  missionQueryService;

    @GetMapping("/api/missions/location")
    public ApiResponse<   MissionResDTO.MissionListResult> findByLocationName(String locationName, Pageable pageable) {
        MissionResDTO.MissionListResult result = missionQueryService.findByLocationName(locationName, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
