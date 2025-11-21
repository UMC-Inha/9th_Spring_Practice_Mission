package umc.domain.user.dto.req;

import jakarta.validation.constraints.NotNull;
import umc.domain.user.enums.MissionStatus;

public class UserMissionReqDTO {

    public record JoinMissionReqDTO(
            @NotNull
            Long missionId
    ){}

    public record CompleteMissionDTO(
       @NotNull
       MissionStatus status
    ){}
}
