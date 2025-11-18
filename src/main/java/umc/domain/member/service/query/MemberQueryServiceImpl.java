package umc.domain.member.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.enums.Status;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.member.repository.MemberRepository;

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
    public Page<MissionListDto> getMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findMissionsByMemberId(memberId, pageable);
    }

    @Override
    public Page<MissionChallengeListDto> getAvailableMissions(Long memberId, String regionName,
                                                              Long lastMissionId, Pageable pageable) {
        return memberMissionRepository
                .findAvailableMissionsByMemberIdAndRegion(regionName, memberId, lastMissionId, pageable);
    }
}

