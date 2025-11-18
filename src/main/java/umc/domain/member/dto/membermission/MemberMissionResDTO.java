package umc.domain.member.dto.membermission;

import java.time.LocalDate;
import lombok.Builder;
import umc.domain.member.enums.Status;

public class MemberMissionResDTO {

    @Builder
    public record challengeMissionResDTO(
            Long missionId,
            String missionDescription,
            Long memberId,
            Status status,
            LocalDate endDate
    ) {
    }
}
