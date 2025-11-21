package umc.domain.user.dto.res;

import lombok.Builder;
import umc.domain.user.enums.MissionStatus;

import java.time.LocalDateTime;

public class UserMissionResDTO {

    @Builder
    public record JoinMissionResDTO(
            Long userMissionId,
            Long missionId,
            MissionStatus status,
            LocalDateTime createdAt
    ){}
}
