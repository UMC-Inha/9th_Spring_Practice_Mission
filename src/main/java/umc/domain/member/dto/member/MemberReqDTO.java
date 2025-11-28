package umc.domain.member.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import umc.domain.member.enums.Gender;
import umc.global.annotation.ExistFoods;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotBlank
            String address,
            @NotNull
            LocalDate birth,
            @ExistFoods
            List<Long> preferCategory
    ) {
    }
}
