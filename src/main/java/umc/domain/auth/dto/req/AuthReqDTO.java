package umc.domain.auth.dto.req;

import umc.domain.member.enums.Address;
import umc.domain.member.enums.Gender;
import umc.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    public record SignUpDTO(
       String name,
       Gender gender,
       LocalDate  birth,
       Address address,
       String detailAddress,
       @ExistFoods
       List<Long> preferCategory

    ){}
}
