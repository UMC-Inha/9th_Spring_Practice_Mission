package umc.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import umc.domain.mission.entity.MissionStatus;

public class MemberMissionResDTO {

    @Builder
    public record SearchListDTO(
            List<SearchDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record SearchDTO(
            Long missionId,
            String content,
            Integer point,
            LocalDateTime acceptedAt,
            LocalDateTime successAt,
            MissionStatus status,
            LocalDateTime deadlineAt
    ) {
    }


}
