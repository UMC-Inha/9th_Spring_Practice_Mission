package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;
import umc.domain.member.enums.Status;
import umc.domain.member.exception.member.code.MemberSuccessCode;
import umc.domain.member.service.command.MemberCommandService;
import umc.domain.member.service.query.MemberQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;
import umc.global.dto.PageResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/auth/members/signup")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberCommandService.signUp(dto));
    }

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
    public ApiResponse<PageResponse<MissionListDto>> getMemberMissions(
            @PathVariable Long memberId,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        PageResponse<MissionListDto> missionList = memberQueryService.getMissions(memberId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionList);
    }

    @GetMapping("{memberId}/missions/challenge")
    public ApiResponse<PageResponse<MissionChallengeListDto>> getAvailableMissions(
            @PathVariable Long memberId,
            @RequestParam String regionName,
            @RequestParam Long lastMissionId,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        PageResponse<MissionChallengeListDto> missionList = memberQueryService
                .getAvailableMissions(memberId, regionName, lastMissionId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionList);
    }
}
