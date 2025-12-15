package umc.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import umc.domain.member.enums.Gender;
import umc.global.annotation.ExistCategory;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            Gender gender,
            LocalDate birth,
            Long regionId,
            String detailAddress,
            @ExistCategory
            List<Long> preferCategory
    ){}

    //로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}

}
