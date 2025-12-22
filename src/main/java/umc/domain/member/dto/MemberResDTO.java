package umc.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;


public class MemberResDTO {

    @Builder
    public record MyPageMemberDTO(
            String nickname,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}

}
