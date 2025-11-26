package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.Res.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.annotation.PageCheck;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionController {
    private final MissionQueryService missionQueryService;

    // 특정 가게의 미션 목록 조회
    @GetMapping("/{storeId}")
    public ApiResponse<MissionResDTO.MissionByStoreListDTO> getMissionByStore(
            @PathVariable Long storeId,
            @PageCheck @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMissionByStoreListDTO(storeId, page)
        );
    }
}
