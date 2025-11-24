package umc.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionCreateReq {
        @NotNull private LocalDate deadline;
        @NotBlank private String conditional;
        @NotNull private Integer Point;
        @NotNull private Integer leastAmount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetChallengeMissionReqDTO{
        @NotNull private Long  missionId;
    }
}
