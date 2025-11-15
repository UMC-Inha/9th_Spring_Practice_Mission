package umc.domain.member.dto;

import java.time.LocalDate;
import java.util.List;
import umc.domain.member.entity.MemberGender;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            String email,
            String password,
            String phoneNumber,
            String address,
            LocalDate birth,
            MemberGender gender,
            List<AgreementDTO> agreements,
            List<Long> preferredFoods
    ) {}

    public record AgreementDTO(
            Long policyId,
            boolean agreed
    ) {}

}
