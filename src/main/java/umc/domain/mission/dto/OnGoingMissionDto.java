package umc.domain.mission.dto;

import java.time.LocalDate;

public record OnGoingMissionDto(
        String storeName,
        Integer point,
        LocalDate deadline,
        Integer leastAmount
) {
}