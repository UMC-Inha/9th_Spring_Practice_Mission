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

    @Builder
    public record goingResDTO(
            String storeName,
            String content,
            Integer point,
            Integer period,
            LocalDate deadline
    ){}

    @Builder
    public record completeResDTO(
            String storeName,
            String content,
            Integer point
    ){}
}
