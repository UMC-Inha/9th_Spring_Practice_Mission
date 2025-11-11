package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.enums.Status;
import umc.domain.member.service.query.MemberQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberQueryService;

    @GetMapping("/mypage/{memberId}")
    public ApiResponse<MyPageDto> getMyPage(
            @PathVariable Long memberId
    ) {
        MyPageDto myPageDto = memberQueryService.getMyPage(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, myPageDto);
    }

    @GetMapping("{memberId}/missions/count")
    public ApiResponse<Long> getMissionCount(
            @PathVariable Long memberId,
            @RequestParam String regionName,
            @RequestParam Status status
    ) {
        Long missionCount = memberQueryService.countMissionsByRegionAndStatus(memberId, regionName, status);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionCount);
    }

    @GetMapping("{memberId}/missions")
    public ApiResponse<Page<MissionListDto>> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MissionListDto> missionList = memberQueryService.getMissions(memberId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionList);
    }

    @GetMapping("{memberId}/missions/challenge")
    public ApiResponse<Page<MissionChallengeListDto>> getAvailableMissions(
            @PathVariable Long memberId,
            @RequestParam String regionName,
            @RequestParam Long lastMissionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MissionChallengeListDto> missionList = memberQueryService
                .getAvailableMissions(memberId, regionName, lastMissionId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionList);
    }
}
