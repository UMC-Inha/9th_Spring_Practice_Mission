package umc.domain.mission.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionCreateReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionCreateReq {
        private LocalDate deadline;
        private String conditional;
        private Integer Point;
        private Integer leastAmount;
    }
}
