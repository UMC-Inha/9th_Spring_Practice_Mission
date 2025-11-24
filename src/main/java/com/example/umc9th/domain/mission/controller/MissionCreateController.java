package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionCreateDTO;
import com.example.umc9th.domain.mission.service.MissionCreateService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")

public class MissionCreateController {

    private final MissionCreateService missionCreateService;

    @PostMapping("/{storeId}")
    public ApiResponse<Long> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionCreateDTO dto
    ) {
        MissionCreateDTO d = new MissionCreateDTO(
                storeId,
                dto.getConditional(),
                dto.getPoint(),
                dto.getDeadline()
        );

        Long missionId = missionCreateService.createMission(d);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionId);
    }
}
