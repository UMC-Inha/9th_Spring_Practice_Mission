package umc.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;
import umc.domain.member.enums.Gender;


public class MemberResDTO {
    @Builder
    public record Mypage (
            Long id,
        String name,
        Gender gender
    ){}
}
