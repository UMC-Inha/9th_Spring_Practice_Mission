package umc.domain.member.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.enums.Status;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.membermission.MemberMissionRepository;
import umc.global.dto.PageResponse;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MyPageDto getMyPage(Long memberId) {
        return memberRepository.findMyPageById(memberId);
    }

    @Override
    public Long countMissionsByRegionAndStatus(Long memberId, String regionName, Status status) {
        return memberMissionRepository.getMissionCountByMemberIdAndRegion(memberId, regionName, status);
    }

    @Override
    public PageResponse<MissionListDto> getMissions(Long memberId, Pageable pageable) {
        Page<MissionListDto> page = memberMissionRepository.findMissionsByMemberId(memberId, pageable);
        return PageResponse.of(page);
    }

    @Override
    public PageResponse<MissionChallengeListDto> getAvailableMissions(Long memberId, String regionName,
                                                                      Long lastMissionId, Pageable pageable) {

        Page<MissionChallengeListDto> page = memberMissionRepository
                .findAvailableMissionsByMemberIdAndRegion(regionName, memberId, lastMissionId, pageable);
        return PageResponse.of(page);
    }
}

