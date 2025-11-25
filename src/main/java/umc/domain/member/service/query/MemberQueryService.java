package umc.domain.member.service.query;

import org.springframework.data.domain.Pageable;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.enums.Status;
import umc.global.dto.PageResponse;

public interface MemberQueryService {
    MyPageDto getMyPage(Long memberId);

    Long countMissionsByRegionAndStatus(Long memberId, String regionName, Status status);

    PageResponse<MissionListDto> getMissions(Long memberId, Pageable pageable);

    PageResponse<MissionChallengeListDto> getAvailableMissions(Long memberId,
                                                               String regionName,
                                                               Long lastMissionId,
                                                               Pageable pageable);
}
