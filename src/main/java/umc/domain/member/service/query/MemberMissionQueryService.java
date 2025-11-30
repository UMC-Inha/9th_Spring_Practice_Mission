package umc.domain.member.service.query;

import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.global.apiPayload.dto.PageResponseDTO;

public interface MemberMissionQueryService {
    PageResponseDTO<MemberMissionResDTO.goingResDTO> getRunningMissions(Long memberId, Integer page);

}
