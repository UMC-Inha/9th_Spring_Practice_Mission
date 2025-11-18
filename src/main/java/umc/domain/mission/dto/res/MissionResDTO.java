package umc.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record AddMissionResDTO(
            Long missionId,
            LocalDateTime createdAt
    ){}
}
