package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.code.MissionSuccessCode;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.service.MissionQueryService;
import umc.domain.review.code.ReviewSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionQueryService missionQueryService;

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<PageResponseDTO<MissionResponseDTO>>> getAvailableMissions(
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
        PageResponseDTO<MissionResponseDTO> result =
                missionQueryService.getAvailableMissions(regionId, memberId, pageable);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.MISSION_SEARCH_SUCCESS, result)
        );
    }
}
