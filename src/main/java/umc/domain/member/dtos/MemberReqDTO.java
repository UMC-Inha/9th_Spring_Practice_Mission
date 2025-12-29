package umc.domain.member.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import umc.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address

    ){}

    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
