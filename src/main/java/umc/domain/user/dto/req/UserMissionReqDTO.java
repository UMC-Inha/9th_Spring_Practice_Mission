package umc.domain.user.dto.req;

import jakarta.validation.constraints.NotNull;

public class UserMissionReqDTO {

    public record JoinMissionReqDTO(
            @NotNull
            Long missionId
    ){}
}
