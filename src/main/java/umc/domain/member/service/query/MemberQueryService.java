package umc.domain.member.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.enums.Status;

public interface MemberQueryService {
    MyPageDto getMyPage(Long memberId);

    Long countMissionsByRegionAndStatus(Long memberId, String regionName, Status status);

    Page<MissionListDto> getMissions(Long memberId, Pageable pageable);

    Page<MissionChallengeListDto> getAvailableMissions(Long memberId,
                                                       String regionName,
                                                       Long lastMissionId,
                                                       Pageable pageable);
}
