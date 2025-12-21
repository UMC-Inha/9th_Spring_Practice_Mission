package umc.domain.member.dtos;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            String email,
            String name,
            String role
    ) {}

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
