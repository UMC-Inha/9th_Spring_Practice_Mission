package umc.domain.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.service.command.MissionCommandService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResDTO.AddMissionResDTO> addMission(
            @RequestBody @Valid MissionReqDTO.AddMissionReqDTO req
            ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionCommandService.addMission(req)
        );
    }
}
