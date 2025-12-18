package umc.domain.member.dto;

import java.time.LocalDateTime;
import lombok.Builder;


public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ) {
    }

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
