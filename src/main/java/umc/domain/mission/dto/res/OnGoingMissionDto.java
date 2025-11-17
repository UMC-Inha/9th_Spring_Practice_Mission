package umc.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OnGoingMissionDto(
        String storeName,
        Integer point,
        LocalDate deadline,
        Integer leastAmount
) {
}