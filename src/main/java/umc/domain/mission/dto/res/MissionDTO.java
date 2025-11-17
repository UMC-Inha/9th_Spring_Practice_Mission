package umc.domain.mission.dto.res;

import lombok.Builder;

@Builder
public record  MissionDTO (
     long MissionId,
    String storeName,
    Integer least_amount,
    Integer point,
    String locationName,
    int deadline
){ }
