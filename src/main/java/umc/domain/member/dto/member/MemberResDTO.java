package umc.domain.member.dto.member;

import java.time.LocalDateTime;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {
    }

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ) {
    }
}
