package umc.domain.mission.service;

import umc.domain.mission.dto.res.GetChallengeMissionResDTO;

public interface MemberMissionCommandService {
    GetChallengeMissionResDTO getChallengeMission(Long memberId, Long missionId);
}
