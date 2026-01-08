package umc.domain.member.service.query;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;
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

    MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
