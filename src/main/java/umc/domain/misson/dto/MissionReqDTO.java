package umc.domain.misson.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class MissionReqDTO {

    public record createMissionReqDTO(
            @NotBlank
            String description,
            @PositiveOrZero
            int points,
            @NotNull
            LocalDate endDate
    ) {
    }
}
