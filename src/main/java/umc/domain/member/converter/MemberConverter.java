package umc.domain.member.converter;

import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberReqDTO.JoinDTO dto) {
        return Member.builder()
                .name(dto.name())
                .password(dto.password())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }
}
