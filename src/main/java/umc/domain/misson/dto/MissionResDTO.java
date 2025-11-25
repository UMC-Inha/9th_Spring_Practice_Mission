package umc.domain.misson.dto;

import java.time.LocalDate;
import java.util.List;
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

    @Builder
    public record MissionPreviewDTO(
            String description,
            Integer points,
            LocalDate endDate
    ) {
    }

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionPreviewDTOList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}
