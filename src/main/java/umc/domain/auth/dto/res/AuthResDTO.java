package umc.domain.auth.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class AuthResDTO{

    @Builder
    public record SignUpDTO(
            Long memberId,
            LocalDateTime created_at

    ){}
}
