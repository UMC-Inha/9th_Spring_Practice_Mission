package umc.domain.user.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {

    @Builder
    public record JoinDTO(
            Long userId,
            LocalDateTime createAt
    ){}
}
