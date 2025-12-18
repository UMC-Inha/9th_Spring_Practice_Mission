package umc.domain.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import umc.domain.member.entity.MemberGender;
import umc.global.annotation.ExistFoods;
import umc.global.annotation.ValidGender;

public class MemberReqDTO {

    public record JoinDTO(

            @NotBlank(message = "이름은 필수 입력값입니다.")
            String name,

            @Email(message = "올바른 이메일 형식이 아닙니다.")
            @NotBlank(message = "이메일은 필수 입력값입니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            String password,

            @NotBlank(message = "전화번호는 필수 입력값입니다.")
            String phoneNumber,

            @NotBlank(message = "주소는 필수 입력값입니다.")
            String address,

            @NotNull(message = "생년월일은 필수 입력값입니다.")
            LocalDate birth,

            @NotNull(message = "성별은 필수 입력값입니다.")
            @ValidGender
            MemberGender gender,

            @NotNull(message = "약관 동의 정보는 필수입니다.")
            List<AgreementDTO> agreements,

            @NotNull(message = "선호 음식 목록은 null일 수 없습니다.")
            @ExistFoods
            List<Long> preferredFoods
    ) {}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}

    public record AgreementDTO(
            Long policyId,
            boolean agreed
    ) {}

}
