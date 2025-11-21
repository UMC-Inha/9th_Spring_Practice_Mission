package umc.domain.member.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.enums.Gender;
import umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record SignupRequestDTO(
            @NotBlank(message = "이름은 필수 입력 값입니다.")
            String name,
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
            String password,
            Gender gender,
            LocalDate birthday,
            String address,
            String phone,
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,
            @ExistFoods
            List<Long> preferCategory
    ){

    }
}
