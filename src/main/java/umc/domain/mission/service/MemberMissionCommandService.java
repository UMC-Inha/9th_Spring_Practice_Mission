package umc.domain.mission.service;

import umc.domain.mission.dto.res.MissionResDTO;

public interface MemberMissionCommandService {
    MissionResDTO.GetChallengeMissionResDTO getChallengeMission(Long memberId, Long missionId);
}
