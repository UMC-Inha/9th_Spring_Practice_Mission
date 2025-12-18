package umc.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.command.MissionCommandService;
import umc.domain.mission.service.query.MissionQueryService;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/stores/{storeId}/missions")
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping
    public ApiResponse<MissionResDTO.CreateDTO> createMission(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @Valid @RequestBody MissionReqDTO.CreateDTO request
    ) {
        MissionResDTO.CreateDTO response =
                missionCommandService.createMission(memberId, storeId, request);

        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, response);
    }

    @Override
    @GetMapping
    public ApiResponse<MissionResDTO.SearchListDTO> getMission(
            @PathVariable Long storeId,
            @RequestParam @ValidPage Integer page
    ){
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.getMission(storeId, page));
    }
}