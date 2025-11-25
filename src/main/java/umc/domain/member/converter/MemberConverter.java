package umc.domain.member.converter;

import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberStatus;
import umc.domain.member.entity.MemberType;

public class MemberConverter {

    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .email(dto.email())
                .point(0)
                .memberType(MemberType.USER)
                .phoneNumber(dto.phoneNumber())
                .memberStatus(MemberStatus.ACTIVE)
                .build();

    }
}
