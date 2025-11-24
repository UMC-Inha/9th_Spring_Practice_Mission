package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.service.MissionCreateService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MissionCreateController {
    private final MissionCreateService missionCreateService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionDetailDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.MissionCreateReq req
    ) {
        MissionResDTO.MissionDetailDTO  result = missionCreateService.createMission(storeId, req);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
