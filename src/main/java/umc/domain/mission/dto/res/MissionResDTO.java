package umc.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record AddMissionResDTO(
            Long missionId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MissionByStoreListResDTO(
            List<MissionByStoreResDTO> missionList,
            Integer listSize, //페이징 단위
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast

    ){}

    @Builder
    public record MissionByStoreResDTO(
            Long missionId,
            Integer rewardPoint,
            Integer baseAmount,
            LocalDate deadline,
            Boolean isActive
    ){}
}
