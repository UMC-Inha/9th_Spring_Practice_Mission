package umc.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.code.MissionSuccessCode;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.service.command.MissionCommandService;
import umc.domain.mission.service.query.MissionQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @GetMapping("/missions/available")
    public ResponseEntity<ApiResponse<PageResponseDTO<MissionResDTO.MissionResponseDTO>>> getAvailableMissions(
            @RequestParam Long regionId,
            @RequestParam Long memberId,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ) {
        PageResponseDTO<MissionResDTO.MissionResponseDTO> result =
                missionQueryService.getAvailableMissions(regionId, memberId, pageable);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.MISSION_SEARCH_SUCCESS, result)
        );
    }

    @PostMapping("/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.CreateResDTO>> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateReqDTO dto
            ){
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATE_SUCCESS,missionCommandService.createMission(dto))
        );
    }
}
