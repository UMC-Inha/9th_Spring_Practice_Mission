package umc.domain.member.converter;

import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.global.auth.enums.Role;

public class MemberConverter {

    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberReqDTO.JoinDTO dto, String password, Role role) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
