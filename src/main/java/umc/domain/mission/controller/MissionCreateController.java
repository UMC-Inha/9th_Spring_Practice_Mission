package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.req.MissionCreateReqDTO;
import umc.domain.mission.dto.res.MissionDetailDTO;
import umc.domain.mission.service.MissionCreateService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MissionCreateController {
    private final MissionCreateService missionCreateService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionDetailDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionCreateReqDTO.MissionCreateReq req
    ) {
        MissionDetailDTO  result = missionCreateService.createMission(storeId, req);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
