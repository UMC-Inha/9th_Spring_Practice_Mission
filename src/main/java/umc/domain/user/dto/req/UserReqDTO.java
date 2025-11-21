package umc.domain.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.domain.user.enums.Gender;
import umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record JoinDto(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
