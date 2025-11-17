package umc.domain.mission.dto.res;

import lombok.Builder;

@Builder
public record CompletedMissionDto(
        Long missionId,
        String storeName,
        Integer point,
        Integer leastAmount
) {
}