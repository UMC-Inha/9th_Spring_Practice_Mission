package umc.domain.mission.dto.req;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class MissionReqDTO {

    public record AddMissionReqDTO(
            @NotNull
            Long storeId,

            @NotNull
            @Positive
            Integer rewardPoint,

            @NotNull
            @Positive
            Integer baseAmount,

            @NotNull
            @Future //이 날짜는 무조건 미래여야해!
            LocalDate deadline

    ){}

}
