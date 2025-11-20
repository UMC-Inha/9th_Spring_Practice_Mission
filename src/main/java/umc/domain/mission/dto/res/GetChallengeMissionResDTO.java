package umc.domain.mission.dto.res;

import lombok.Builder;

@Builder
public record GetChallengeMissionResDTO(
        Long memberMissionId,
        Long memberId,
        Long missionId,
        Boolean isCompleted
){}