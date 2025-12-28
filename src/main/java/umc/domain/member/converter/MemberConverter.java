package umc.domain.member.converter;

import umc.domain.member.dto.req.MemberReqDTO;
import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.entity.Member;
import umc.global.auth.enums.Role;

public class MemberConverter {

    public static MemberResponseDTO.SearchDTO toSearchDTO(Member member) {
        return new MemberResponseDTO.SearchDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getAddress(),
                member.getPoint()
        );
    }
    // Entity -> DTO
    public static MemberResponseDTO.SignupResponseDTO toSignupDTO(Member member) {
        return MemberResponseDTO.SignupResponseDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }
    // DTO -> Entity
    public static Member toMember(MemberReqDTO.SignupRequestDTO dto, String password,Role role) {
        return Member.builder()
                .name(dto.name())
                .password(password)
                .gender(dto.gender())
                .role(role)
                .birthday(dto.birthday())
                .address(dto.address())
                .email(dto.email())
                .phone(dto.phone())
                .build();
    }

    public static MemberResponseDTO.LoginDTO toLoginDTO(
            Member member,
            String accessToken
    ) {
        return MemberResponseDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

}
