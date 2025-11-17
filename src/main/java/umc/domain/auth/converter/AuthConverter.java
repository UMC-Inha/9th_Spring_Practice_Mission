package umc.domain.auth.converter;

import umc.domain.auth.dto.req.AuthReqDTO;
import umc.domain.auth.dto.res.AuthResDTO;
import umc.domain.member.dto.res.MemberResDTO;
import umc.domain.member.entity.Member;

public class AuthConverter {

    // Entity -> DTO
    public static AuthResDTO.SignUpDTO toSIgnUpDTO(
            Member member
    ){
        return AuthResDTO.SignUpDTO.builder()
                .memberId(member.getId())
                .created_at(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            AuthReqDTO.SignUpDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
