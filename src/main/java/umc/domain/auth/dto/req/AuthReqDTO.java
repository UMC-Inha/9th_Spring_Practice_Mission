package umc.domain.auth.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.domain.member.enums.Address;
import umc.domain.member.enums.Gender;
import umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    public record SignUpDTO(
            @NotBlank String name,
            @NotNull Gender gender,
            @NotNull LocalDate birth,
            @NotNull Address address,
            @NotBlank String detailAddress,
            @NotNull @ExistFoods List<Long> preferCategory
    ){}
}
