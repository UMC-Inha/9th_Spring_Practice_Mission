package umc.domain.member.dto.res;

import lombok.Builder;
import umc.domain.member.enums.State;

import java.time.LocalDate;

public class MemberMissionResDTO {

    @Builder
    public record createResDTO(
            Long memberMissionId,
            LocalDate deadline,
            State state
    ){}
}
