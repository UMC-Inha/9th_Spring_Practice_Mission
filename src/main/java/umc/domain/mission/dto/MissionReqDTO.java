package umc.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MissionReqDTO {

    public record CreateDTO(
            @NotBlank(message = "미션 내용은 필수입니다.")
            String content,

            @NotNull(message = "포인트는 필수입니다.")
            Integer point,

            @NotNull(message = "마감일(deadlineAt)은 필수입니다.")
            LocalDateTime deadlineAt
    ) {}
}
