package umc.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MissionDetailDTO(
        Long missionId,
        Long storeId,
        String storeName,
        LocalDate deadline,
        String conditional,
        Integer point,
        Integer leastAmount
){}
