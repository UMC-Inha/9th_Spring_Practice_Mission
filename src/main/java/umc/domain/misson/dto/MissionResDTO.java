package umc.domain.misson.dto;

import java.time.LocalDate;
import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record createMissionResDTO(
            Long missionId,
            String description,
            int points,
            int uniqueNumber,
            LocalDate endDate,
            Long storeId
    ) {
    }
}
