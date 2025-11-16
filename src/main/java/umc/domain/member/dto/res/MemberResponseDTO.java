package umc.domain.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDTO {

    public record SearchDTO(
            Long id,
            String name,
            String email,
            String address,
            Integer point
    ){}

    @Builder
    public record SignupResponseDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

}
