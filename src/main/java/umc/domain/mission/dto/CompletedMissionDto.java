package umc.domain.mission.dto;

public record CompletedMissionDto(
        Long missionId,
        String storeName,
        Integer point,
        Integer leastAmount
) {
}