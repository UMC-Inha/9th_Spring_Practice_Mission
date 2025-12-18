package umc.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import umc.domain.mission.entity.Mission;

public class MissionResDTO {

    @Builder
    public record CreateDTO(
            Long missionId,
            Long storeId,
            String content,
            Integer point,
            LocalDateTime deadlineAt
    ) {
        public static CreateDTO from(Mission mission) {
            return new CreateDTO(
                    mission.getId(),
                    mission.getStore().getId(),
                    mission.getContent(),
                    mission.getPoint(),
                    mission.getDeadlineAt()
            );
        }
    }

    @Builder
    public record SearchListDTO(
            List<SearchDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){

    }

    @Builder
    public record SearchDTO(
            Long missionId,
            String content,
            Integer point,
            LocalDateTime deadlineAt
    ) {
    }



}
