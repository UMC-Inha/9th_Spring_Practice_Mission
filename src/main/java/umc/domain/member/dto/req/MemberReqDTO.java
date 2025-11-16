package umc.domain.member.dto.req;

import umc.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

    public record SignupRequestDTO(
            String name,
            String password,
            Gender gender,
            LocalDate birthday,
            String address,
            String phone,
            String email
    ){

    }
}
